package com.jmuet.algo;

public class Util {

    public static int[] strToArray(String str) {
        int[] res = new int[str.length()];
        for (int i = 0; i < str.length(); i++)
            res[i] = Character.getNumericValue(str.charAt(i));
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
        if (i < 0)
            throw new IllegalArgumentException("works for input >= 0");
        if (i == 0)
            return new int[]{0};
        int tmp = i;
        int[] res = new int[1 + (int)Math.floor(Math.log(i)/Math.log(10))];
        for (int k = res.length - 1; k >= 0; k--) {
            res[k] = tmp % 10;
            tmp = tmp / 10;
        }
        return res;
    }
}
