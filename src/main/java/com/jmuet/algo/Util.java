package com.jmuet.algo;

public class Util {

    public static int[] strToArray(String str0) {
        int signum = 1;
        String str;
        if (str0.charAt(0) == '-') {
            signum = -1;
            str = str0.substring(1);
        } else
            str = str0;
        int[] res = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            int numericValue = Character.getNumericValue(str.charAt(i));
            if (numericValue < 0)
                throw new IllegalArgumentException("input is not a valid number");
            res[i] = numericValue;
        }
        res[0] = res[0] * signum;
        return res;
    }

    public static String arrayToStr(int[] array) {
        StringBuilder builder = new StringBuilder();
        for (int i : array) {
            builder.append(i);
        }
        return builder.toString();
    }

    public static int[] toArray(int i) {
        if (i == 0)
            return new int[]{0};
        int signum = i < 0 ? -1 : 1;
        int tmp = signum < 0 ? -i : i;
        int[] res = new int[1 + (int)Math.floor(Math.log(Math.abs(i))/Math.log(10))];
        for (int k = res.length - 1; k >= 0; k--) {
            res[k] = tmp % 10;
            tmp = tmp / 10;
        }
        res[0] = res[0] * signum;
        return res;
    }
}
