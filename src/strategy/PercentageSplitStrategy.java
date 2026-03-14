package strategy;

import dto.expense.Expense;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public void splitAccordingly(Expense expense) {
        double totalAmount = expense.getAmount();
        System.out.println("splitAccordingly PERCENTAGE amount=" + totalAmount);
        expense.getSplits().forEach((split) -> split.setVal(split.getVal() * totalAmount / 100));
    }
}
