package facade;

import constants.SplitType;
import dto.expense.Expense;
import dto.expense.ExpenseUpdateRequest;
import dto.expense.MoneyTransfer;
import dto.expense.Split;
import dto.group.Group;
import dto.group.GroupUpdateRequest;
import dto.user.User;
import dto.user.UserUpdateRequest;
import factory.SplitFactory;
import manager.BalanceManager;
import manager.ExpenseManager;
import manager.GroupManager;
import manager.PaymentManager;
import manager.UserManager;
import strategy.SplitStrategy;

import java.util.List;

public class SplitwiseFacade {

    private final UserManager userManager;
    private final GroupManager groupManager;
    private final ExpenseManager expenseManager;
    private final BalanceManager balanceManager;
    private final PaymentManager paymentManager;

    public SplitwiseFacade() {
        this.userManager = new UserManager();
        this.groupManager = new GroupManager();
        this.expenseManager = new ExpenseManager();
        this.balanceManager = BalanceManager.getInstance();
        this.paymentManager = PaymentManager.getInstance();
    }

    public void addUser(User user) {
        System.out.println("addUser userId=" + user.getUserId());
        userManager.createProfile(user);
    }

    public void updateUser(UserUpdateRequest request) {
        System.out.println("updateUser userId=" + request.getId());
        userManager.editProfile(request);
    }

    public void removeUser(User user) {
        System.out.println("removeUser userId=" + user.getUserId());
        userManager.deleteProfile(user);
    }

    public void createGroup(Group group) {
        System.out.println("createGroup groupId=" + group.getGroupId());
        groupManager.createGroup(group);
    }

    public void updateGroup(GroupUpdateRequest request) {
        System.out.println("updateGroup groupId=" + request.getGroupId());
        groupManager.editGroup(request);
    }

    public void deleteGroup(int groupId) {
        System.out.println("deleteGroup groupId=" + groupId);
        groupManager.deleteGroup(groupId);
    }

    public void addExpense(Expense expense, SplitType splitType) {
        System.out.println("addExpense expenseId=" + expense.getExpenseId() + " groupId=" + expense.getGroupId() + " splitType=" + splitType);
        SplitStrategy strategy = SplitFactory.getSplitStrategy(splitType);
        strategy.splitAccordingly(expense);
        expenseManager.createExpense(expense);
        balanceManager.updateExpenses(expense);
    }

    public void updateExpense(ExpenseUpdateRequest request) {
        System.out.println("updateExpense expenseId=" + request.getExpenseId() + " groupId=" + request.getGroupId());
        expenseManager.editExpense(request);
    }

    public void deleteExpense(int groupId, int expenseId) {
        System.out.println("deleteExpense groupId=" + groupId + " expenseId=" + expenseId);
        expenseManager.deleteExpenseById(groupId, expenseId);
    }

    public void recordPayment(MoneyTransfer transfer) {
        System.out.println("recordPayment transferId=" + transfer.getTransferId() + " from=" + transfer.getFromId() + " to=" + transfer.getToId() + " groupId=" + transfer.getGroupId());
        paymentManager.recordPayment(transfer);
    }

    public static void main(String[] args) {
        System.out.println("main starting");
        SplitwiseFacade facade = new SplitwiseFacade();
        facade.addUser(new User(1, "Alice", "alice@x.com", null, null));
        facade.addUser(new User(2, "Bob", "bob@x.com", null, null));
        facade.createGroup(new Group(1, "Trip", List.of(1, 2)));
        Expense expense = new Expense(1, 1, "Dinner", 100, 1, 1, List.of(new Split(2, 0)), SplitType.EQUAL);
        facade.addExpense(expense, SplitType.EQUAL);
        facade.recordPayment(new MoneyTransfer(1, 2, 1, 1, 1));
    }
}