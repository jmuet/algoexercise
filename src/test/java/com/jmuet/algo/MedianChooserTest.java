package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.*;

public class MedianChooserTest {

    @Test
    public void choosesFirst() {
        int res = MedianChooser.chooseMedianPosition(new int[]{6,5,2,9,3,7,1,0,8}, 0, 8);
        assertEquals(0, res);
    }

    @Test
    public void choosesLast() {
        int res = MedianChooser.chooseMedianPosition(new int[]{8,9,3,0,6}, 0, 4);
        assertEquals(4, res);
    }

    @Test
    public void choosesMiddle() {
        int res = MedianChooser.chooseMedianPosition(new int[]{8,9,6,0,3}, 0, 4);
        assertEquals(2, res);
    }

    @Test
    public void choosesMiddleFromSubset() {
        int res = MedianChooser.chooseMedianPosition(new int[]{6,5,2,9,3,7,1,0,8}, 1, 7);
        assertEquals(4, res);
    }

    @Test
    public void choosesMiddleFromSubset2() {
        int res = MedianChooser.chooseMedianPosition(new int[]{6,5,3,9,2,7,1,0,8}, 2, 6);
        assertEquals(4, res);
    }

    @Test
    public void choosesMiddleFromSubset_withEvenElements() {
        int res = MedianChooser.chooseMedianPosition(new int[]{6,5,3,2,7,1,0,8}, 2, 5);
        assertEquals(3, res);
    }

    @Test
    public void choosesFirstFromSubset() {
        int res = MedianChooser.chooseMedianPosition(new int[]{5,6,2,9,3,7,1,0,8}, 0, 2);
        assertEquals(0, res);
    }

}