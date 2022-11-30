package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private  Database database;
    private final static Person pesho = new Person(1, "Pesho");
    private final static Person toshko = new Person(2, "Toshko");
    private final static Person goshko = new Person(3, "Goshko");
    @Before
    public void prepare() throws OperationNotSupportedException {
        database = new Database(pesho, toshko, goshko);
    }

    // testing FindByUserName
    @Test(expected = OperationNotSupportedException.class)
    public void tesFindByUserNameShouldThrowWithNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFinedByUserNameShouldThrowIfMissing() throws OperationNotSupportedException {
        database.findByUsername("Sasho");
    }

    @Test
    public void testFinedByUsernameShouldReturnUser() throws OperationNotSupportedException {
        Person person = database.findByUsername(pesho.getUsername());
        Assert.assertEquals(person.getId(), pesho.getId());
        Assert.assertEquals(person.getUsername(), pesho.getUsername());
    }



}