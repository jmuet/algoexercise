package com.jmuet.algo;

public class GradeSchoolAdd {

    public static String add(String x, String y) {
        return Util.arrayToStr(add(Util.strToArray(x), Util.strToArray(y)));
    }

    public static int[] add(int[] x, int[] yo) {
        int[] res = new int[x.length];
        int[] y = new int[x.length];
        System.arraycopy(yo,0,y,y.length-yo.length,yo.length);
        int carry = 0;
        for (int i = x.length - 1; i >= 0; i--) {
            int a = x[i] + y[i] + carry;
            if (a > 10) {
                a = a - 10;
                carry = 1;
            } else {
                carry = 0;
            }
            res[i] = a;
        }
        if (carry != 0) {
            int[] res1 = new int[x.length + 1];
            res1[0] = carry;
            System.arraycopy(res,0,res1,1,res.length);
            return res1;
        }
        return res;
    }

    public static String sub(String x, String y) {
        return Util.arrayToStr(sub(Util.strToArray(x), Util.strToArray(y)));
    }

    public static int[] sub(int[] x, int[] yo) {
        int[] y = new int[x.length];
        System.arraycopy(yo,0,y,y.length-yo.length,yo.length);
        int[] res = new int[x.length];
        boolean carry = false;
        for (int i = x.length - 1; i >= 0; i--) {
            int a = x[i] - y[i] - (carry ? 1 : 0);
            if (a < 0) {
                a = a + 10;
                carry = true;
            } else {
                carry = false;
            }
            res[i] = a;
        }
        return res;
    }

}
