package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class QuickSortTest {

    @Test
    public void sortsArrayChoosingAlwaysFirstPivot() {
        int[] unsorted = new int[]{3,7,1,4,0,8,2};
        int[] expected = new int[]{0,1,2,3,4,7,8};

        QuickSort operation = QuickSort.withChoosingFirst(unsorted);
        QuickSort.SortResult res = operation.sort();
        assertArrayEquals(expected, res.getResult());
        assertEquals(11, res.getComparisons());
        assertEquals(9, res.getCalls());
    }

    @Test
    public void sortsArrayChoosingAlwaysLastPivot() {
        int[] unsorted = new int[]{3,7,1,4,0,8,2};
        int[] expected = new int[]{0,1,2,3,4,7,8};

        QuickSort operation = QuickSort.withChoosingLast(unsorted);
        QuickSort.SortResult res = operation.sort();
        assertArrayEquals(expected, res.getResult());
        assertEquals(13, res.getComparisons());
        assertEquals(11, res.getCalls());
    }

}