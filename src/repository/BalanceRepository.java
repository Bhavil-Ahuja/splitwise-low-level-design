package repository;

import dto.expense.Due;

import java.util.HashMap;
import java.util.Map;

public class BalanceRepository {
    static volatile BalanceRepository instance;
    Map<Integer, Map<Integer, Map<Integer, Map<Integer, Due>>>> userBalancesInAGroup = new HashMap<>();

    public static BalanceRepository getInstance() {
        if (instance == null) {
            synchronized (BalanceRepository.class) {
                if (instance == null) {
                    instance = new BalanceRepository();
                }
            }
        }
        return instance;
    }

    public void updateBalancesByGroupId(int groupId, int expenseId, Map<Integer, Map<Integer, Due>> updatedTransactions) {
        System.out.println("updateBalancesByGroupId groupId=" + groupId + " expenseId=" + expenseId);
        userBalancesInAGroup.get(groupId).put(expenseId, updatedTransactions);
    }

    public Map<Integer, Map<Integer, Due>> getBalancesByExpenseId(int groupId, int expenseId) {
        System.out.println("getBalancesByExpenseId groupId=" + groupId + " expenseId=" + expenseId);
        if (!userBalancesInAGroup.containsKey(groupId)) {
            userBalancesInAGroup.put(groupId, new HashMap<>());
        }
        if (!userBalancesInAGroup.get(groupId).containsKey(expenseId)) {
            userBalancesInAGroup.get(groupId).put(expenseId, new HashMap<>());
        }
        return userBalancesInAGroup.get(groupId).get(expenseId);
    }
}
