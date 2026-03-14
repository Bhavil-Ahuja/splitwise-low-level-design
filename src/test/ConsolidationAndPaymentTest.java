package test;

import constants.DueType;
import constants.ExpenseStatus;
import constants.SplitType;
import dto.expense.Due;
import dto.expense.Expense;
import dto.expense.Split;
import facade.SplitwiseFacade;
import repository.BalanceRepository;
import repository.ExpenseRepository;
import repository.TransactionRepository;

import java.util.List;
import java.util.Map;

public class ConsolidationAndPaymentTest {

    public static void main(String[] args) {
        System.out.println("=== Consolidation and recordPayment test ===\n");
        SplitwiseFacade facade = new SplitwiseFacade();

        facade.addUser(new dto.user.User(1, "Alice", "a@x.com", null, null));
        facade.addUser(new dto.user.User(2, "Bob", "b@x.com", null, null));
        facade.addUser(new dto.user.User(3, "Carol", "c@x.com", null, null));
        facade.createGroup(new dto.group.Group(1, "Trip", List.of(1, 2, 3)));

        Expense exp1 = new Expense(1, 1, "Dinner", 300, 1, 1,
                List.of(new Split(1, 0), new Split(2, 0), new Split(3, 0)), SplitType.EQUAL);
        facade.addExpense(exp1, SplitType.EQUAL);

        Expense exp2 = new Expense(2, 1, "Cab", 60, 2, 2,
                List.of(new Split(2, 0), new Split(3, 0)), SplitType.EQUAL);
        facade.addExpense(exp2, SplitType.EQUAL);

        Expense exp3 = new Expense(3, 1, "Snacks", 90, 3, 3,
                List.of(new Split(1, 0), new Split(2, 0), new Split(3, 0)), SplitType.EQUAL);
        facade.addExpense(exp3, SplitType.EQUAL);

        BalanceRepository balanceRepo = BalanceRepository.getInstance();

        Map<Integer, Map<Integer, Due>> bal1 = balanceRepo.getBalancesByExpenseId(1, 1);
        assertDue(bal1, 1, 2, 100, DueType.OWED, "exp1: Alice owed by Bob 100");
        assertDue(bal1, 1, 3, 100, DueType.OWED, "exp1: Alice owed by Carol 100");
        assertDue(bal1, 2, 1, 100, DueType.OWE, "exp1: Bob owes Alice 100");
        assertDue(bal1, 3, 1, 100, DueType.OWE, "exp1: Carol owes Alice 100");
        System.out.println("PASS: Expense 1 consolidation (300/3 each)");

        Map<Integer, Map<Integer, Due>> bal2 = balanceRepo.getBalancesByExpenseId(1, 2);
        assertDue(bal2, 2, 3, 30, DueType.OWED, "exp2: Bob owed by Carol 30");
        assertDue(bal2, 3, 2, 30, DueType.OWE, "exp2: Carol owes Bob 30");
        System.out.println("PASS: Expense 2 consolidation (60/2 each)");

        Map<Integer, Map<Integer, Due>> bal3 = balanceRepo.getBalancesByExpenseId(1, 3);
        assertDue(bal3, 3, 1, 30, DueType.OWED, "exp3: Carol owed by Alice 30");
        assertDue(bal3, 3, 2, 30, DueType.OWED, "exp3: Carol owed by Bob 30");
        assertDue(bal3, 1, 3, 30, DueType.OWE, "exp3: Alice owes Carol 30");
        assertDue(bal3, 2, 3, 30, DueType.OWE, "exp3: Bob owes Carol 30");
        System.out.println("PASS: Expense 3 consolidation (90/3 each)");

        facade.recordPayment(new dto.expense.MoneyTransfer(1, 2, 1, 1, 1));
        facade.recordPayment(new dto.expense.MoneyTransfer(2, 3, 2, 1, 2));
        facade.recordPayment(new dto.expense.MoneyTransfer(3, 1, 3, 1, 3));

        ExpenseRepository expenseRepo = ExpenseRepository.getInstance();
        assertStatus(expenseRepo.findExpenseById(1, 1), ExpenseStatus.CLOSE, "expense 1 CLOSE");
        assertStatus(expenseRepo.findExpenseById(1, 2), ExpenseStatus.CLOSE, "expense 2 CLOSE");
        assertStatus(expenseRepo.findExpenseById(1, 3), ExpenseStatus.CLOSE, "expense 3 CLOSE");
        System.out.println("PASS: All three expenses marked CLOSE after recordPayment");

        List<dto.expense.MoneyTransfer> txs = TransactionRepository.getInstance().getAllTransactionsInAGroup(1);
        if (txs == null || txs.size() != 3) {
            throw new AssertionError("Expected 3 transactions in group 1, got " + (txs == null ? "null" : txs.size()));
        }
        System.out.println("PASS: TransactionRepository has 3 recorded payments for group 1");

        System.out.println("\n=== All validations passed ===");
    }

    static void assertDue(Map<Integer, Map<Integer, Due>> balances, int from, int to, double amount, DueType type, String msg) {
        if (!balances.containsKey(from) || !balances.get(from).containsKey(to)) {
            throw new AssertionError(msg + " missing: no due from " + from + " to " + to);
        }
        Due d = balances.get(from).get(to);
        if (Math.abs(d.getAmount() - amount) > 1e-9) {
            throw new AssertionError(msg + " wrong amount: expected " + amount + " got " + d.getAmount());
        }
        if (d.getDueType() != type) {
            throw new AssertionError(msg + " wrong type: expected " + type + " got " + d.getDueType());
        }
    }

    static void assertStatus(Expense e, ExpenseStatus expected, String msg) {
        if (e == null) {
            throw new AssertionError(msg + ": expense is null");
        }
        if (e.getExpenseStatus() != expected) {
            throw new AssertionError(msg + ": expected " + expected + " got " + e.getExpenseStatus());
        }
    }
}
