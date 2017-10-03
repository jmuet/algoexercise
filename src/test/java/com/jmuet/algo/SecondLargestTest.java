package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SecondLargestTest {

    @Test
    public void findsSecondLargest() {
        assertEquals(4, SecondLargest.second(new int[]{8,2,3,4}));
        assertEquals(4, SecondLargest.second(new int[]{1,1,1,1,8,2,3,4}));
        assertEquals(14, SecondLargest.second(new int[]{18,12,13,14,10,9,8,7}));
        assertEquals(4, SecondLargest.second(new int[]{8, 4}));
        assertEquals(12, SecondLargest.second(new int[]{9,8,7,6,10,11,12,13,1,2,3,4,0,1,0,2}));
    }

    @Test
    public void rejectsNonPowerOf2() {
        try {
            SecondLargest.second(new int[]{1,2,3});
            fail("should have thrown exception");
        } catch (IllegalArgumentException e) {
            assertEquals("expecting array with length being power of 2, greater than 1", e.getMessage());
        }
    }

    @Test
    public void rejectsSingleItem() {
        try {
            SecondLargest.second(new int[]{1});
            fail("should have thrown exception");
        } catch (IllegalArgumentException e) {
            assertEquals("expecting array with length being power of 2, greater than 1", e.getMessage());
        }
    }

}