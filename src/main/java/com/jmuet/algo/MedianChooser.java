package com.jmuet.algo;

public class MedianChooser {

    //end exclusive
    public static int chooseMedianPosition(int[] a, int start, int end) {
        int max = -1;
        int maxPos = -1;
        int secondMax = -1;
        int secondMaxPos = -1;

        int[] candidates = new int[]{start, end, start + (end - start) / 2};
        for (int candidate : candidates)
            if (a[candidate] >= max) {
                secondMax = max;
                secondMaxPos = maxPos;
                max = a[candidate];
                maxPos = candidate;
            } else if (a[candidate] >= secondMax) {
                secondMax = a[candidate];
                secondMaxPos = candidate;
            }
        return secondMaxPos;
    }

}
