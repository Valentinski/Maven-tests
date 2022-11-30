package Chainblock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    private Chainblock database;

    @Before
    public void setUp(){
        this.database = new ChainblockImpl();
    }

    @Test
    public void testAddTransaction(){
        Transaction transaction1 = addOneTransaction();
        Assert.assertEquals(1, this.database.getCount());
        Assert.assertTrue(this.database.contains(transaction1));
    }



    @Test
    public void testAddExistingTransaction(){
        Transaction transaction1 = addOneTransaction();
        Assert.assertEquals(1, this.database.getCount());
        Assert.assertTrue(this.database.contains(transaction1.getId()));
        this.database.add(transaction1);
        Assert.assertEquals(1, this.database.getCount());
    }

    @Test
    public void testChangeTransactionStatus(){
        addOneTransaction();
        Assert.assertEquals(1, this.database.getCount());
        this.database.changeTransactionStatus(1, TransactionStatus.ABORTED);
       Assert.assertEquals(TransactionStatus.ABORTED, this.database.getById(1).getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusInvalidID(){
        addOneTransaction();
        this.database.changeTransactionStatus(2, TransactionStatus.ABORTED);
    }

    @Test
    public void testRemoveTransactionById(){
        Assert.assertEquals(0, this.database.getCount());
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                "Desi", "Stoyan", 150.90);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.ABORTED,
                "Ivan", "Lachezar", 245.50);
        this.database.add(transaction1);
        this.database.add(transaction2);
        Assert.assertEquals(2, this.database.getCount());
        this.database.removeTransactionById(1);
        Assert.assertEquals(1, this.database.getCount());
        Assert.assertFalse(this.database.contains(1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveInvalidTransaction(){
        addOneTransaction();
        this.database.removeTransactionById(5);
    }

    @Test
    public void testGetTransactionById(){
        Transaction transaction1 = addOneTransaction();
        Transaction returnedTransaction = this.database.getById(1);
        Assert.assertEquals(transaction1, returnedTransaction);
        Assert.assertEquals(transaction1.getId(), returnedTransaction.getId());
        Assert.assertEquals(transaction1.getStatus(), returnedTransaction.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTransactionByInvalidId(){
        addOneTransaction();
        this.database.getById(5);
    }

    @Test
    public void testGetByTransactionStatus(){
        Assert.assertEquals(0, this.database.getCount());
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                "Desi", "Stoyan", 150.90);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.ABORTED,
                "Ivan", "Lachezar", 245.50);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL,
                "Valio", "Yanchi", 482.5);

        this.database.add(transaction1);
        this.database.add(transaction2);
        this.database.add(transaction3);
        Assert.assertEquals(3, this.database.getCount());

        Iterable<Transaction> result = this.database.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<Transaction> returnedTransaction = new ArrayList<>();
        result.forEach(returnedTransaction::add);

        Assert.assertEquals(2, returnedTransaction.size());
        returnedTransaction.forEach(tr -> Assert.assertEquals(TransactionStatus.SUCCESSFUL, tr.getStatus()));

        Assert.assertEquals(returnedTransaction.get(0), transaction2);
        Assert.assertEquals(returnedTransaction.get(1), transaction1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByInvalidTransactionStatus(){
        Assert.assertEquals(0, this.database.getCount());
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                "Desi", "Stoyan", 150.90);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.ABORTED,
                "Ivan", "Lachezar", 245.50);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL,
                "Valio", "Yanchi", 482.5);

        this.database.add(transaction1);
        this.database.add(transaction2);
        this.database.add(transaction3);
        Assert.assertEquals(3, this.database.getCount());

        Iterable<Transaction> result = this.database.getByTransactionStatus(TransactionStatus.FAILED);
    }

    private Transaction addOneTransaction() {
        Assert.assertEquals(0, this.database.getCount());
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                "Desi", "Stoyan", 150.90);
        this.database.add(transaction1);
        return transaction1;
    }
}