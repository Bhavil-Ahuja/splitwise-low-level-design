package dto.expense;

import constants.DueType;

public class Due {
    double amount;
    DueType dueType;

    public Due(double amount, DueType dueType) {
        this.amount = amount;
        this.dueType = dueType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DueType getDueType() {
        return dueType;
    }

    public void setDueType(DueType dueType) {
        this.dueType = dueType;
    }
}
