package manager;

import dto.expense.Expense;
import dto.expense.ExpenseUpdateRequest;
import repository.ExpenseRepository;

public class ExpenseManager {
    ExpenseRepository expenseRepository;

    public ExpenseManager() {
        expenseRepository = ExpenseRepository.getInstance();
    }

    public void createExpense(Expense expense) {
        System.out.println("createExpense expenseId=" + expense.getExpenseId() + " groupId=" + expense.getGroupId());
        expenseRepository.addExpense(expense);
    }

    public void deleteExpenseById(int groupId, int expenseId) {
        System.out.println("deleteExpenseById groupId=" + groupId + " expenseId=" + expenseId);
        expenseRepository.removeExpenseById(groupId, expenseId);
    }

    public void editExpense(ExpenseUpdateRequest expenseUpdateRequest) {
        System.out.println("editExpense expenseId=" + expenseUpdateRequest.getExpenseId() + " groupId=" + expenseUpdateRequest.getGroupId());
        Expense expense = expenseRepository.findExpenseById(expenseUpdateRequest);
        if (expenseUpdateRequest.getAmount() > 0) {
            expense.setAmount(expenseUpdateRequest.getAmount());
        }
        if (expenseUpdateRequest.getDesc() != null) {
            expense.setDesc(expenseUpdateRequest.getDesc());
        }
        if (expenseUpdateRequest.getSplits() != null) {
            expense.setSplits(expenseUpdateRequest.getSplits());
        }
        if (expenseUpdateRequest.getCreditorUserId() > 0) {
            expense.setCreditorUserId(expenseUpdateRequest.getCreditorUserId());
        }
        if (expenseUpdateRequest.getSplitType() != null) {
            expense.setSplitType(expenseUpdateRequest.getSplitType());
        }
        expenseRepository.save(expense);
    }
}
