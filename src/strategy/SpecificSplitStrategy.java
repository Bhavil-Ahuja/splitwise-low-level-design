package strategy;

import dto.expense.Expense;

public class SpecificSplitStrategy implements SplitStrategy {
    @Override
    public void splitAccordingly(Expense expense) {
        System.out.println("splitAccordingly EXACT expenseId=" + expense.getExpenseId());
    }
}
