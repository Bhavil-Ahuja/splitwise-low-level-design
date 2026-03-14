package repository;

import dto.expense.MoneyTransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {
    static volatile TransactionRepository instance;
    static Map<Integer, List<MoneyTransfer>> transactions;

    public static TransactionRepository getInstance() {
        if (instance == null) {
            synchronized (TransactionRepository.class) {
                if (instance == null) {
                    instance = new TransactionRepository();
                    transactions = new HashMap<>();
                }
            }
        }
        return instance;
    }

    public List<MoneyTransfer> getAllTransactionsInAGroup(int groupId) {
        System.out.println("getAllTransactionsInAGroup groupId=" + groupId);
        return transactions.get(groupId);
    }

    public void recordPayment(MoneyTransfer moneyTransfer) {
        System.out.println("recordPayment transferId=" + moneyTransfer.getTransferId() + " groupId=" + moneyTransfer.getGroupId());
        if (!transactions.containsKey(moneyTransfer.getGroupId())) {
            transactions.put(moneyTransfer.getGroupId(), new ArrayList<>());
        }
        transactions.get(moneyTransfer.getGroupId()).add(moneyTransfer);
    }
}
