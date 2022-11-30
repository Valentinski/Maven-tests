package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;
    private final static Integer[] NUMBERS = {7, 3, 3, 1};


    @Before
    public void prepare() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }
    //Constructor testing
    @Test
    public void testConstructorCreateValidDB() throws OperationNotSupportedException {
        Integer[] dbElements = database.getElements();
        // How to test two arrays if they are equal
        Assert.assertArrayEquals(NUMBERS, dbElements);
    }
    // testing array capacity and exception
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWithMoreThan16elements() throws OperationNotSupportedException {
        Integer[] bigArray = new Integer[17];
        new Database(bigArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWithNoElements() throws OperationNotSupportedException {
        Integer[] emptyArray =  new Integer[0];
        new Database(emptyArray);
    }

    //testing add Method
    // null
    @Test(expected = OperationNotSupportedException.class)
    public void addNullShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testShouldAddElement() throws OperationNotSupportedException {
        database.add(42);
        Integer [] dbElements = database.getElements();
        Assert.assertEquals(dbElements[dbElements.length - 1], Integer.valueOf(42));
        Assert.assertEquals(dbElements.length, NUMBERS.length + 1);
    }

    // testing remove method

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowWithEmptyBase() throws OperationNotSupportedException {
        //we empty array
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        // we check if the array is empty
        database.remove();
    }

    // we compare two operations before and after
    @Test
    public void testRemoveShouldRemove() throws OperationNotSupportedException {
        Integer[] elementsBeforeRemove = database.getElements();
        database.remove();
        Integer[] elementsAfterRemove = database.getElements();
        Assert.assertEquals(elementsBeforeRemove.length-1, elementsAfterRemove.length);
        int previousSecondToLastElement = elementsBeforeRemove[elementsBeforeRemove.length-2];
        int currentLastElement = elementsAfterRemove[elementsAfterRemove.length - 1];
        Assert.assertEquals(previousSecondToLastElement, currentLastElement);

    }
}