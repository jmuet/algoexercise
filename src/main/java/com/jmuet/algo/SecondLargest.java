package com.jmuet.algo;

import java.util.Arrays;

public class SecondLargest {

    //aims at (n + logn - 2) comparisons
    public static int second(int[] input) {
        int comparisons = 0;
        if (input.length < 2 || ((input.length & (input.length - 1)) != 0 ))
            throw new IllegalArgumentException("expecting array with length being power of 2, greater than 1");

        int tournamentRounds = log2(input.length);
        int[][] tournament = new int[tournamentRounds + 1][input.length];

        tournament[0] = Arrays.copyOf(input, input.length);

        int pairs = input.length / 2;
        for (int r = 0; r < tournamentRounds; r++) {
//            System.out.println(String.format("------ round: %d ------", r));
            for (int k = 0; k < pairs; k++) {
                int a = getValue(tournament, r, 2 * k);
//                System.out.println(String.format("valA %d at index %d", a, 2 * k));
                int b = getValue(tournament, r, 2 * k + 1);
//                System.out.println(String.format("valB %d at index %d", b, 2 * k + 1));
                tournament[r + 1][k] = a > b ? 2 * k : 2 * k + 1;
//                System.out.println(String.format("winner is: %d at index %d", a > b ? a : b, a > b ? 2 * k : 2 * k + 1));
                comparisons++;
            }
            pairs = pairs / 2;
        }

//        int winnerPosition = getValue(tournament, tournamentRounds - 1, 0) > getValue(tournament, tournamentRounds - 1, 1) ? 0 : 1;
//        int secondLargest = getValue(tournament, tournamentRounds - 1, winnerPosition == 0 ? 1 : 0);

        int secondLargest = -1;
        int r = tournamentRounds;
        int winnerPosition = tournament[tournamentRounds][0];

        while (r > 0) {
            int challenger = getValue(tournament, r - 1, winnerPosition % 2 == 0 ? winnerPosition + 1 : winnerPosition - 1);
//            System.out.println(String.format("challenger %d from pos %d in round %d", challenger, winnerPosition % 2 == 0 ? winnerPosition + 1 : winnerPosition - 1, r - 1));
            if (secondLargest < challenger)
                secondLargest = challenger;
            comparisons ++;
            r--;
            winnerPosition = tournament[r][winnerPosition];
        }

        System.out.println(String.format("array length %d required %d comparisons, expected %d", input.length, comparisons, input.length + log2(input.length) - 2));
        return secondLargest;

    }

    private static int log2(int x)
    {
        return (int) (Math.log(x) / Math.log(2));
    }

    private static int getValue(int[][] t, int r, int pos) {
        int i = r;
        int position = pos;
        while (i > 0) {
            position = t[i][position];
            i--;
        }
        return t[0][position];
    }

}
