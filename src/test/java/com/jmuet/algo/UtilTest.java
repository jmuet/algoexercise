package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UtilTest {

    @Test
    public void convertsToArray() {
        assertArrayEquals(new int[]{1,2}, Util.toArray(12));
        assertArrayEquals(new int[]{3,1,2}, Util.toArray(312));
        assertArrayEquals(new int[]{4,4,1,2}, Util.toArray(4412));
        assertArrayEquals(new int[]{-8,1,2}, Util.toArray(-812));
    }

    @Test
    public void convertsToString() {
        assertEquals("12", Util.arrayToStr( new int[]{1,2}));
        assertEquals("-987", Util.arrayToStr( new int[]{-9,8,7}));
    }

    @Test
    public void convertsStringToArray() {
        assertArrayEquals(new int[]{1,2}, Util.strToArray("12"));
        assertArrayEquals(new int[]{-9,2,0}, Util.strToArray("-920"));
    }

    @Test
    public void getsClosestPowersOfTwo() {
        assertEquals(1, Util.getClosestPowerOfTwo(1));
        assertEquals(2, Util.getClosestPowerOfTwo(2));
        assertEquals(4, Util.getClosestPowerOfTwo(3));
        assertEquals(8, Util.getClosestPowerOfTwo(5));
    }

}