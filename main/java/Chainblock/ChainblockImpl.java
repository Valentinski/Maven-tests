package Chainblock;

import java.util.*;

public class ChainblockImpl implements Chainblock{

    //database with transaction
    private Map<Integer, Transaction> transactionMap;
    public ChainblockImpl(){
        this.transactionMap = new HashMap<>();
    }

    public int getCount() {
        return transactionMap.size();
    }

    public void add(Transaction transaction) {
        if(!contains(transaction)){
            this.transactionMap.put(transaction.getId(), transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return this.transactionMap.containsValue(transaction);
    }

    public boolean contains(int id) {
        return this.transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if(!contains(id)){
            throw new IllegalArgumentException("Invalid transaction id.");
        }

        Transaction transactionForChange = this.transactionMap.get(id);
        transactionForChange.setStatus(newStatus);

    }

    public void removeTransactionById(int id) {
        if(!contains(id)){
            throw new IllegalArgumentException("Invalid transaction id.");
        }

        this.transactionMap.remove(id);
    }

    public Transaction getById(int id) {
        if(!contains(id)){
            throw  new IllegalArgumentException("Invalid transaction id.");
        }

        return this.transactionMap.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : this.transactionMap.values()) {
            if (transaction.getStatus() == status){
                filteredTransactions.add(transaction);
            }
        }
        if(filteredTransactions.size() == 0){
            throw new IllegalArgumentException("Transaction with given status not found.");
        }
        filteredTransactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
        return filteredTransactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return null;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
