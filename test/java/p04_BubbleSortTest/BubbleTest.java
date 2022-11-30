package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void testBubbleSort(){
        int [] numbers = {3, 1, 5, 2, -4, -38};
        int [] sortedArray = {-38, -4, 1, 2, 3, 5};
        Bubble.sort(numbers);
        Assert.assertArrayEquals(sortedArray, numbers);


    }

}