package com.jmuet.algo;

import java.util.Arrays;
import java.util.function.BiFunction;

public class QuickSort {

    private int comparisons;
    private int[] array;
    private BiFunction<Integer, Integer, Integer> choosePivotPosition;


    private QuickSort(int[] array, BiFunction<Integer, Integer, Integer> choosePivotPosition) {
        this.array = Arrays.copyOf(array, array.length);
        this.choosePivotPosition = choosePivotPosition;
        this.comparisons = 0;
    }

    public static QuickSort withChoosingFirst(int[] array) {
        return new QuickSort(array, (start, end) -> {
            return start;
        } );
    }

    public static class SortResult {
        int[] result;
        int comparisons;

        public SortResult(int[] result, int comparisons) {
            this.result = result;
            this.comparisons = comparisons;
        }

        public int[] getResult() {
            return result;
        }

        public int getComparisons() {
            return comparisons;
        }
    }

    public SortResult sort() {
        sort(0, array.length);
        return new SortResult(array, comparisons);
    }

    //end is exclusive
    private void sort(int start, int end) {
        int length = end - start;
        if (length <= 1)
            return;
        comparisons += length - 1;
        int pivotPosition = choosePivotPosition.apply(start, end);
        int pivotPositionAfterPartition = partition(start, end, pivotPosition);
        sort(start, pivotPositionAfterPartition);
        sort(pivotPositionAfterPartition + 1, end);
    }

    private int partition(int start, int end, int pivotPosition) {
        int pivot = array[pivotPosition];
        swap(array, start, pivotPosition);
        int i = start + 1;
        for (int j = start + 1; j < end; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        int finalPivotPosition = --i;
        swap(array, start, finalPivotPosition);
        return finalPivotPosition;
    }

    private static void swap(int[] array, int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

}
