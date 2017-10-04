package com.jmuet.algo;

import java.util.Arrays;

import static com.jmuet.algo.Util.arrayToStr;
import static com.jmuet.algo.Util.strToArray;

public class Karatsuba {

    public static String multiply(String x, String y) {
        if (x.length() != y.length())
            throw new IllegalArgumentException("input lengths must be the same");
        verifyPowerOfTwo(x);
        return arrayToStr(multiplyRecursive(strToArray(x), strToArray(y)));
    }

    private static int[] multiplyRecursive(int[] x, int[] y) {
        int len = x.length;
        if (len == 1)
            return Util.toArray(x[0] * y[0]);

        int[] a = Arrays.copyOf(x, len/2);
        int[] b = Arrays.copyOfRange(x, len/2, len);
        int[] c = Arrays.copyOf(y, len/2);
        int[] d = Arrays.copyOfRange(y, len/2, len);

        int[] p = GradeSchoolAdd.add(a, b);
        int[] q = GradeSchoolAdd.add(c, d);

        if (p.length > len || q.length > len) { //there was a carry
            p = padLeft(p, 2 * len);
            q = padLeft(q, 2 * len);
        }

        int[] ac = multiplyRecursive(a, c);
        int[] bd = multiplyRecursive(b, d);
        int[] pq = multiplyRecursive(p, q);

        int[] adbc = GradeSchoolAdd.sub(pq, GradeSchoolAdd.add(ac, bd));

        //recursively ac, bd, pq
        //adbc = pq - ac - bd
        //return 10^n ac + 10^n/2 adbc + bd
        return GradeSchoolAdd.add(GradeSchoolAdd.add(padRight(ac, ac.length + len), padRight(adbc, adbc.length + len /2)), bd);
    }

    private static int[] padRight(int[] orig, int desiredLength) {
        int[] res = new int[desiredLength];
        System.arraycopy(orig, 0, res, 0, orig.length);
        return res;
    }

    private static int[] padLeft(int[] orig, int desiredLength) {
        int[] res = new int[desiredLength];
        System.arraycopy(orig, 0, res, desiredLength - orig.length, orig.length);
        return res;
    }

    private static void verifyPowerOfTwo(String n) {
        if ((n.length() & n.length() - 1) != 0)
            throw new IllegalArgumentException("input length not power of 2");
    }

}
