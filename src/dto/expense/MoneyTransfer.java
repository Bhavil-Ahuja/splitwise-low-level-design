package dto.expense;

public class MoneyTransfer {
    final int transferId;
    final int fromId;
    final int toId;
    final int groupId;
    final int expenseId;

    public MoneyTransfer(int transferId, int fromId, int toId, int groupId, int expenseId) {
        this.transferId = transferId;
        this.fromId = fromId;
        this.toId = toId;
        this.groupId = groupId;
        this.expenseId = expenseId;
    }

    public int getTransferId() {
        return transferId;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getExpenseId() {
        return expenseId;
    }
}
