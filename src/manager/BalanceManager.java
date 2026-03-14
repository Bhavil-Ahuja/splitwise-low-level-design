package manager;

import constants.DueType;
import dto.expense.Due;
import dto.expense.Expense;
import dto.expense.Split;
import repository.BalanceRepository;

import java.util.HashMap;
import java.util.Map;

public class BalanceManager {
    static volatile BalanceManager instance;
    static BalanceRepository balanceRepository;

    public static BalanceManager getInstance() {
        if (instance == null) {
            synchronized (BalanceManager.class) {
                if (instance == null) {
                    instance = new BalanceManager();
                    balanceRepository = BalanceRepository.getInstance();
                }
            }
        }
        return instance;
    }

    public void updateExpenses(Expense expense) {
        System.out.println("updateExpenses expenseId=" + expense.getExpenseId() + " groupId=" + expense.getGroupId());
        Map<Integer, Map<Integer, Due>> peopleInThisSplit = balanceRepository.getBalancesByExpenseId(expense.getGroupId(), expense.getExpenseId());

        for (Split split : expense.getSplits()) {
            int creditorId = expense.getCreditorUserId();
            int owerId = split.getOwerUserId();
            double amount = split.getVal();
            if (!peopleInThisSplit.containsKey(creditorId))  {
                peopleInThisSplit.put(creditorId, new HashMap<>());
            }
            if (!peopleInThisSplit.containsKey(owerId)) {
                peopleInThisSplit.put(owerId, new HashMap<>());
            }
            peopleInThisSplit.get(creditorId).put(owerId, new Due(amount, DueType.OWED));
            peopleInThisSplit.get(owerId).put(creditorId, new Due(amount, DueType.OWE));
        }
        peopleInThisSplit = consolidateBalances(peopleInThisSplit);
        balanceRepository.updateBalancesByGroupId(expense.getGroupId(), expense.getExpenseId(), peopleInThisSplit);
    }

    public Map<Integer, Map<Integer, Due>> consolidateBalances(Map<Integer, Map<Integer, Due>> peopleInSplit) {
        Map<Integer, Map<Integer, Due>> consolidatedBalances = new HashMap<>();
        for (Integer person1Id : peopleInSplit.keySet()) {
            Map<Integer, Due> newDues = new HashMap<>();
            for (Integer person2Id : peopleInSplit.get(person1Id).keySet()) {
                Due thisDue = peopleInSplit.get(person1Id).get(person2Id);
                if (!newDues.containsKey(person2Id)) {
                    newDues.put(person2Id, new Due(thisDue.getAmount(), thisDue.getDueType()));
                } else {
                    Due existingDue = peopleInSplit.get(person1Id).get(person2Id);
                    if (thisDue.getDueType().equals(DueType.OWED)) {
                        existingDue.setAmount(getEffectiveValue(thisDue) + existingDue.getAmount());
                        if (existingDue.getAmount() > 0) {
                            existingDue.setDueType(DueType.OWED);
                        } else  {
                            existingDue.setDueType(DueType.OWE);
                        }
                    }
                }
            }
            consolidatedBalances.put(person1Id, newDues);
        }
        return consolidatedBalances;
    }

    public double getEffectiveValue(Due due) {
        if (due.getDueType().equals(DueType.OWED)) {
            return due.getAmount();
        } else {
            return -due.getAmount();
        }
    }
}
