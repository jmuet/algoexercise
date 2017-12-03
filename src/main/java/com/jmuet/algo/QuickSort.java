package com.jmuet.algo;

public class QuickSort {

    public static void sort(int [] array) {
        sort(array, 0, array.length);
    }

    //end is exclusive
    private static void sort(int[] array, int start, int end) {
        if (end - start <= 1)
            return;
        int pivotPosition = choosePivotPosition(start, end);
        int pivotPositionAfterPartition = partition(array, start, end, pivotPosition);
        sort(array, start, pivotPositionAfterPartition);
        sort(array, pivotPositionAfterPartition + 1, end);
    }

    private static int choosePivotPosition(int start, int end) {
        return start;
    }

    private static int partition(int[] array, int start, int end, int pivotPosition) {
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
