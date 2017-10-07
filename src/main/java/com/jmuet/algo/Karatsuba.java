package com.jmuet.algo;

import java.util.Arrays;

import static com.jmuet.algo.Util.arrayToStr;
import static com.jmuet.algo.Util.strToArray;

public class Karatsuba {

    public static String multiply(String x, String y) {
        return arrayToStr(multiplyRecursive(strToArray(x), strToArray(y)));
    }

    private static int[] multiplyRecursive(int[] x0, int[] y0) {
        int len = Util.getClosestPowerOfTwo(x0.length > y0.length ? x0.length : y0.length);

        if (len == 1)
            return Util.toArray(x0[0] * y0[0]);

        int[] x = new int[len];
        int[] y = new int[len];
        System.arraycopy(x0,0,x,len-x0.length,x0.length);
        System.arraycopy(y0,0,y,len-y0.length,y0.length);
        int[] a = Arrays.copyOf(x, len/2);
        int[] b = Arrays.copyOfRange(x, len/2, len);
        int[] c = Arrays.copyOf(y, len/2);
        int[] d = Arrays.copyOfRange(y, len/2, len);

        int[] p = GradeSchoolMath.add(a, b);
        int[] q = GradeSchoolMath.add(c, d);

//        if (p.length > len || q.length > len) { //there was a carry
//            p = padLeft(p, 2 * len);
//            q = padLeft(q, 2 * len);
//        }

        int[] ac = multiplyRecursive(a, c);
        int[] bd = multiplyRecursive(b, d);
        int[] pq = multiplyRecursive(p, q);

        int[] adbc = GradeSchoolMath.sub(pq, GradeSchoolMath.add(ac, bd));

        return GradeSchoolMath.add(GradeSchoolMath.add(padRight(ac, ac.length + len), padRight(adbc, adbc.length + len /2)), bd);
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

}
