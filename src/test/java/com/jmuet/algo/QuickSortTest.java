package com.jmuet.algo;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    @Test
    public void sortsArrayChoosingMedianPivot() {
        int[] unsorted = new int[]{3,7,1,4,0,8,2};
        int[] expected = new int[]{0,1,2,3,4,7,8};

        QuickSort operation = QuickSort.withChoosingMedian(unsorted);
        QuickSort.SortResult res = operation.sort();
        System.out.println(Arrays.toString(res.getResult()));
        assertArrayEquals(expected, res.getResult());
        assertEquals(10, res.getComparisons());
        assertEquals(7, res.getCalls());
    }

    @Test
    public void sortsLargeArray_choosingFirst() throws IOException {
        int[] unsorted = loadLargeArrayFromFile();
        QuickSort operation = QuickSort.withChoosingFirst(unsorted);
        QuickSort.SortResult res = operation.sort();
        assertEquals(162085, res.getComparisons());
        assertEquals(13307, res.getCalls());
    }

    @Test
    public void sortsLargeArray_choosingLast() throws IOException {
        int[] unsorted = loadLargeArrayFromFile();
        QuickSort operation = QuickSort.withChoosingLast(unsorted);
        QuickSort.SortResult res = operation.sort();
        assertEquals(164123, res.getComparisons());
        assertEquals(13265, res.getCalls());
    }

    @Test
    public void sortsLargeArray_choosingMedian() throws IOException {
        int[] unsorted = loadLargeArrayFromFile();
        QuickSort operation = QuickSort.withChoosingMedian(unsorted);
        QuickSort.SortResult res = operation.sort();
        assertEquals(138382, res.getComparisons());
        assertEquals(11423, res.getCalls());
    }

    private int[] loadLargeArrayFromFile() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("QuickSort10k.txt").getFile());

        ArrayList<Integer> list = new ArrayList<>(10000);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                int line = scanner.nextInt();
                list.add(line);
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

}