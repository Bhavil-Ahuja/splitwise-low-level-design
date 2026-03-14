package strategy;

import dto.expense.Expense;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public void splitAccordingly(Expense expense) {
        double totalAmount = expense.getAmount();
        int numberOfPeople = expense.getSplits().size();
        double eachSplit = totalAmount / numberOfPeople;
        System.out.println("splitAccordingly EQUAL amount=" + totalAmount + " participants=" + numberOfPeople + " each=" + eachSplit);
        expense.getSplits().forEach((split) -> split.setVal(eachSplit));
    }
}
