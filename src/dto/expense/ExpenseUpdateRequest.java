package dto.expense;

import constants.SplitType;

import java.util.List;

public class ExpenseUpdateRequest {
    final int expenseId;
    final int groupId;
    String desc;
    double amount;
    int creditorUserId;
    List<Split> splits;
    SplitType splitType;

    public ExpenseUpdateRequest(int groupId, int expenseId, String desc, double amount, int creditorUserId, List<Split> splits, SplitType splitType) {
        this.groupId = groupId;
        this.expenseId = expenseId;
        this.desc = desc;
        this.amount = amount;
        this.creditorUserId = creditorUserId;
        this.splits = splits;
        this.splitType = splitType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCreditorUserId() {
        return creditorUserId;
    }

    public void setCreditorUserId(int creditorUserId) {
        this.creditorUserId = creditorUserId;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public int getGroupId() {
        return groupId;
    }
}
