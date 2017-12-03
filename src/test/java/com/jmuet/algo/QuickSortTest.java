package com.jmuet.algo;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void sortsArray() {
        int[] unsorted = new int[]{3,7,1,4,0};
        int[] expected = new int[]{0,1,3,4,7};

        System.out.println(Arrays.toString(unsorted));
        QuickSort.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
        assertArrayEquals(expected, unsorted);
    }

}