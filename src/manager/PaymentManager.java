package manager;

import constants.ExpenseStatus;
import dto.expense.MoneyTransfer;
import repository.BalanceRepository;
import repository.ExpenseRepository;
import repository.TransactionRepository;

public class PaymentManager {
    static volatile PaymentManager instance;
    static TransactionRepository transactionRepository;
    static BalanceRepository balanceRepository;
    static ExpenseRepository expenseRepository;

    public static PaymentManager getInstance() {
        if (instance == null) {
            synchronized (PaymentManager.class) {
                if (instance == null) {
                    instance = new PaymentManager();
                    transactionRepository = TransactionRepository.getInstance();
                    balanceRepository = BalanceRepository.getInstance();
                    expenseRepository = ExpenseRepository.getInstance();
                }
            }
        }
        return instance;
    }

    public void recordPayment(MoneyTransfer moneyTransfer) {
        System.out.println("recordPayment transferId=" + moneyTransfer.getTransferId() + " groupId=" + moneyTransfer.getGroupId());
        transactionRepository.recordPayment(moneyTransfer);
        if (moneyTransfer.getExpenseId() >= 0) {
            var expense = expenseRepository.findExpenseById(moneyTransfer.getGroupId(), moneyTransfer.getExpenseId());
            if (expense != null) {
                expense.setExpenseStatus(ExpenseStatus.CLOSE);
                expenseRepository.save(expense);
                System.out.println("expense marked CLOSE expenseId=" + moneyTransfer.getExpenseId());
            } else {
                System.out.println("expense not found for payment groupId=" + moneyTransfer.getGroupId() + " expenseId=" + moneyTransfer.getExpenseId());
            }
        }
    }
}
