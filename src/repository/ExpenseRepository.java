package repository;

import dto.expense.Expense;
import dto.expense.ExpenseUpdateRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseRepository {
    static volatile ExpenseRepository instance;
    static Map<Integer, Map<Integer, Expense>> expenses = new HashMap<>();

    public static ExpenseRepository getInstance() {
        if (instance == null) {
            synchronized (ExpenseRepository.class) {
                if (instance == null) {
                    instance = new ExpenseRepository();
                }
            }
        }
        return instance;
    }

    public void save(Expense expense) {
        System.out.println("save expenseId=" + expense.getExpenseId() + " groupId=" + expense.getGroupId());
        expenses.get(expense.getGroupId()).put(expense.getExpenseId(), expense);
    }

    public List<Expense> findExpensesByGroupId(int groupId) {
        System.out.println("findExpensesByGroupId groupId=" + groupId);
        return new ArrayList<>(expenses.get(groupId).values());
    }

    public Expense findExpenseById(ExpenseUpdateRequest expenseUpdateRequest) {
        System.out.println("findExpenseById groupId=" + expenseUpdateRequest.getGroupId() + " expenseId=" + expenseUpdateRequest.getExpenseId());
        return expenses.get(expenseUpdateRequest.getGroupId()).get(expenseUpdateRequest.getExpenseId());
    }

    public Expense findExpenseById(int groupId, int expenseId) {
        System.out.println("findExpenseById groupId=" + groupId + " expenseId=" + expenseId);
        return expenses.get(groupId).get(expenseId);
    }

    public void addExpense(Expense expense) {
        System.out.println("addExpense expenseId=" + expense.getExpenseId() + " groupId=" + expense.getGroupId());
        expenses.putIfAbsent(expense.getGroupId(), new HashMap<>());
        expenses.get(expense.getGroupId()).put(expense.getExpenseId(), expense);
    }

    public void removeExpenseById(int groupId, int expenseId) {
        System.out.println("removeExpenseById groupId=" + groupId + " expenseId=" + expenseId);
        expenses.get(groupId).remove(expenseId);
    }
}
