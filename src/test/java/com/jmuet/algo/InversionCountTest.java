package com.jmuet.algo;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class InversionCountTest {

    @Test
    public void zeroInversions_inEmptyArray() {
        assertEquals(0, InversionCount.sortAndCount(new int[]{}).inversionCount);
    }

    @Test
    public void zeroInversions_inOneElementArray() {
        assertEquals(0, InversionCount.sortAndCount(new int[]{1}).inversionCount);
    }

    @Test
    public void countsInversions_inArrayOfLength_ofPowerOfTwo_twoInversions() {
        assertEquals(2, InversionCount.sortAndCount(new int[]{1,3,2,5,4,6,7,8}).inversionCount);
    }

    @Test
    public void countsInversions_inArrayOfLength_ofPowerOfTwo_singleInversion() {
        assertEquals(1, InversionCount.sortAndCount(new int[]{1,2,3,5,4,6,7,8}).inversionCount);
    }

    @Test
    public void countsInversions_inArrayOfLength_ofPowerOfTwo_reverseSorted() {
        assertEquals(28, InversionCount.sortAndCount(new int[]{8,7,6,5,4,3,2,1}).inversionCount);  //8 choose 2 == 28
    }

    @Test
    public void sorts_arrayOfLength_ofPowerOfTwo() {
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8}, InversionCount.sortAndCount(new int[]{1,3,2,5,4,6,7,8}).sorted);
    }

    @Test
    public void countsInversions_evenLengthArray_singleInversion() {
        assertEquals(1, InversionCount.sortAndCount(new int[]{1,2,3,5,4,6}).inversionCount);
    }

    @Test
    public void countsInversions_unEvenLengthArray_twoInversions() {
        assertEquals(2, InversionCount.sortAndCount(new int[]{2,1,3,5,4}).inversionCount);
    }

    @Test
    public void countsInversionsInLargeFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("IntegerArray100k.txt").getFile());

        ArrayList<Integer> list = new ArrayList<>(100000);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                int line = scanner.nextInt();
                list.add(line);
            }
        }

        assertEquals(2407905288l, InversionCount.sortAndCount(list.stream().mapToInt(i -> i).toArray()).inversionCount);
    }
}