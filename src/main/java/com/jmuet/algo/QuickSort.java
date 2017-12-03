package com.jmuet.algo;

import java.util.Arrays;
import java.util.function.BiFunction;

public class QuickSort {

    private int comparisons;
    private int calls;
    private int[] array;
    private BiFunction<int[], Range, Integer> choosePivotPosition;


    private QuickSort(int[] array, BiFunction<int[], Range, Integer> choosePivotPosition) {
        this.array = Arrays.copyOf(array, array.length);
        this.choosePivotPosition = choosePivotPosition;
        this.comparisons = 0;
        this.calls = 0;
    }

    public static QuickSort withChoosingFirst(int[] array) {
        return new QuickSort(array, (arr, range) -> {
            return range.start;
        } );
    }

    public static QuickSort withChoosingLast(int[] array) {
        return new QuickSort(array, (arr, range) -> {
            return range.end; //end inclusive
        } );
    }

    public static QuickSort withChoosingMedian(int[] array) {
        return new QuickSort(array, (arr, range) ->
            MedianChooser.chooseMedianPosition(arr, range.start, range.end) //end inclusive
        );
    }

    private static class Range {
        int start, end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class SortResult {
        int[] result;
        int comparisons, calls;

        public SortResult(int[] result, int comparisons, int calls) {
            this.result = result;
            this.comparisons = comparisons;
            this.calls = calls;
        }

        public int[] getResult() {
            return result;
        }

        public int getComparisons() {
            return comparisons;
        }

        public int getCalls() {
            return calls;
        }
    }

    public SortResult sort() {
        sort(0, array.length);
        return new SortResult(array, comparisons, calls);
    }

    //end is exclusive
    private void sort(int start, int end) {
        calls++;
        int length = end - start;
        if (length <= 1)
            return;
        comparisons += length - 1;
        int pivotPosition = choosePivotPosition.apply(array, new Range(start, end - 1)); //end inclusive
        int pivotPositionAfterPartition = partition(start, end, pivotPosition);
        sort(start, pivotPositionAfterPartition);
        sort(pivotPositionAfterPartition + 1, end);
    }

    //end is exclusive
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
