package com.jmuet.algo;

import java.util.Arrays;

public class InversionCount {

    public static CountResult sortAndCount(int[] input) {
        if (input.length <= 1)
            return new CountResult(input, 0);
        int splitPos = input.length / 2 + input.length % 2;
        CountResult leftInversions = sortAndCount(Arrays.copyOfRange(input, 0, splitPos));
        CountResult rightInversions = sortAndCount(Arrays.copyOfRange(input, splitPos, input.length));
        CountResult splitInversions = mergeAndCount(leftInversions.sorted, rightInversions.sorted);
        return new CountResult(splitInversions.sorted, leftInversions.inversionCount + rightInversions.inversionCount + splitInversions.inversionCount);
    }

    private static CountResult mergeAndCount(int[] left, int[] right) {
        if (left.length < 1)
            return new CountResult(new int[0], 0);
        int[] res = new int[left.length + right.length];
        int i = 0, j = 0, splits = 0;
        for (int k = 0; k < res.length; k++) {
            if (i >= left.length) {
                res[k] = right[j++];
            } else if (j >= right.length || left[i] < right[j]) {
                res[k] = left[i++];
            } else {  //left[i] > right[j]
                res[k] = right[j++];
                splits += left.length - i;
            }
        }
        return new CountResult(res, splits);
    }

    public final static class CountResult {
        int[] sorted;
        long inversionCount;

        public CountResult(int[] sorted, long inversionCount) {
            this.sorted = sorted;
            this.inversionCount = inversionCount;
        }

        @Override
        public String toString() {
            return "CountResult{" +
                    "sorted=" + Arrays.toString(sorted) +
                    ", inversionCount=" + inversionCount +
                    '}';
        }
    }

}
