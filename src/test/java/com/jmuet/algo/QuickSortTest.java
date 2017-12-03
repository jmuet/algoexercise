package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class QuickSortTest {

    @Test
    public void sortsArray() {
        int[] unsorted = new int[]{3,7,1,4,0};
        int[] expected = new int[]{0,1,3,4,7};

        QuickSort operation = QuickSort.withChoosingFirst(unsorted);
        QuickSort.SortResult res = operation.sort();
        assertArrayEquals(expected, res.getResult());
        assertEquals(6, res.getComparisons());
    }

}