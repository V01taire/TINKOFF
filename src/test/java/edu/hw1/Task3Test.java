package edu.hw1;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Task3Test {
    private int[] array1;
    private int[] array2;
    private boolean expected;

    public Task3Test(int[] array1, int[] array2, boolean expected) {
        this.array1 = array1;
        this.array2 = array2;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new int[]{1, 2, 3, 4}, new int[]{0, 6}, true},
            {new int[]{3, 1}, new int[]{4, 0}, true},
            {new int[]{9, 9, 8}, new int[]{8, 9}, false},
            {new int[]{1, 2, 3, 4}, new int[]{2, 3}, false},
            {new int[]{}, new int[]{1, 2, 3}, false},
            {new int[]{1, 2, 3}, new int[]{}, false},
            {new int[]{}, new int[]{}, false}
        });
    }

    @Test
    public void isNestableTest() {
        assertEquals(expected, Task3.isNestable(array1, array2));
    }
}

