package strategy;

import constants.SplitType;
import dto.expense.Expense;
import dto.expense.Split;

import java.util.List;

public interface SplitStrategy {
    void splitAccordingly(Expense expense);
}
