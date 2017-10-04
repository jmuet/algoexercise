package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void convertsToArray() {
        assertArrayEquals(new int[]{1,2}, Util.toArray(12));
        assertArrayEquals(new int[]{3,1,2}, Util.toArray(312));
        assertArrayEquals(new int[]{4,4,1,2}, Util.toArray(4412));
    }

    @Test
    public void convertsToString() {
        assertEquals("12", Util.arrayToStr( new int[]{1,2}));
    }

}