package com.jmuet.algo;

public class GradeSchoolAdd {

    public static String add(String x, String y) {
        return Util.arrayToStr(add(Util.strToArray(x), Util.strToArray(y)));
    }

    public static int[] add(int[] x0, int[] y0) {
        int maxlen = x0.length > y0.length ? x0.length : y0.length;
        int[] x = new int[maxlen];
        int[] y = new int[maxlen];
        System.arraycopy(x0,0,x,maxlen-x0.length,x0.length);
        System.arraycopy(y0,0,y,maxlen-y0.length,y0.length);
        int[] res = new int[maxlen];

        int carry = 0;
        for (int i = maxlen - 1; i >= 0; i--) {
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
            int[] res1 = new int[maxlen + 1];
            res1[0] = carry;
            System.arraycopy(res,0,res1,1,res.length);
            return res1;
        }
        return res;
    }

    public static String sub(String x, String y) {
        return Util.arrayToStr(sub(Util.strToArray(x), Util.strToArray(y)));
    }

    public static int[] sub(int[] x0, int[] y0) {
        int maxlen = x0.length > y0.length ? x0.length : y0.length;
        int[] x = new int[maxlen];
        int[] y = new int[maxlen];
        System.arraycopy(x0,0,x,maxlen-x0.length,x0.length);
        System.arraycopy(y0,0,y,maxlen-y0.length,y0.length);
        int[] res = new int[maxlen];

        int signum = compare(x0, y0);
        if (signum == 0)
            return new int[]{0};
        if (signum < 0) {
            int[] tmp = new int[maxlen];
            System.arraycopy(x,0,tmp,0,maxlen);
            System.arraycopy(y,0,x,0,maxlen);
            System.arraycopy(tmp,0,y,0,maxlen);
        }

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
        res = trimZeroes(res);
        if (signum < 0)
            res[0] = -res[0];
        return res;
    }

    private static int[] trimZeroes(int[] arr) {
        int i = 0;
        while (true) {
            if (i == arr.length - 1)
                return new int[]{0};
            if (arr[i] != 0)
                break;
            i++;
        }
        int[] res = new int[arr.length - i];
        System.arraycopy(arr,i,res,0,res.length);
        return res;
    }

    public static int compare(String x, String y) {
        return compare(Util.strToArray(x), Util.strToArray(y));
    }

    private static int compare(int[] x0, int[] y0) {
        int maxlen = x0.length > y0.length ? x0.length : y0.length;
        int[] x = new int[maxlen];
        int[] y = new int[maxlen];
        System.arraycopy(x0,0,x,maxlen-x0.length,x0.length);
        System.arraycopy(y0,0,y,maxlen-y0.length,y0.length);

        for (int i = 0 ; i < x.length; i++)
            if (x[i] < y[i]) return -1;
        else if (x[i] > y[i]) return 1;
        return 0;
    }

}
