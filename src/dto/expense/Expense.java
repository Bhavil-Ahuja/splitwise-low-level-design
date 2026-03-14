package dto.expense;

import constants.ExpenseStatus;
import constants.SplitType;
import dto.helper.ObjectModificationHelper;

import java.time.LocalDateTime;
import java.util.List;

public class Expense {
    final int expenseId;
    final int groupId;
    String desc;
    double amount;
    final int initiatorUserId;
    int creditorUserId;
    List<Split> splits;
    SplitType splitType;
    ExpenseStatus expenseStatus;
    final LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public Expense(int expenseId, int groupId, String desc, double amount, int initiatorUserId, int creditorUserId, List<Split> owerIds, SplitType splitType) {
        this.expenseId = expenseId;
        this.groupId = groupId;
        this.desc = desc;
        this.amount = amount;
        this.initiatorUserId = initiatorUserId;
        this.creditorUserId = creditorUserId;
        this.splits = owerIds;
        this.splitType = splitType;
        this.expenseStatus = ExpenseStatus.OPEN;
        this.createdAt = this.modifiedAt = LocalDateTime.now();
    }

    public int getExpenseId() {
        return expenseId;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public int getInitiatorUserId() {
        return initiatorUserId;
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
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public ExpenseStatus getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(ExpenseStatus expenseStatus) {
        this.expenseStatus = expenseStatus;
        this.modifiedAt = ObjectModificationHelper.updateModifiedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
