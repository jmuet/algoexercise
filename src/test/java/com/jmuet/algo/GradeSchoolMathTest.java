package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GradeSchoolMathTest {

    @Test
    public void trims_zeroes() {
        assertArrayEquals(new int[]{2,4}, GradeSchoolMath.add(new int[]{0,0,0,0}, new int[]{2,4}));
    }

    @Test
    public void adds_single_digits_1() {
        assertEquals("2", GradeSchoolMath.add("1", "1"));
    }

    @Test
    public void adds_single_digits_2() {
        assertEquals("1", GradeSchoolMath.add("1", "0"));
    }

    @Test
    public void adds_single_digits_3() {
        assertEquals("1", GradeSchoolMath.add("0", "1"));
    }

    @Test
    public void adds_double_digits_1() {
        assertEquals("145", GradeSchoolMath.add("70", "75"));
    }

    @Test
    public void adds_to_zero() {
        assertEquals("145", GradeSchoolMath.add("0", "145"));
    }

    @Test
    public void adds_negative_1() {
        assertEquals("5", GradeSchoolMath.add("80", "-75"));
    }

    @Test
    public void adds_negative_2() {
        assertEquals("-5", GradeSchoolMath.add("-80", "75"));
    }

    @Test
    public void adds_negative_both() {
        assertEquals("-100", GradeSchoolMath.add("-8", "-92"));
    }

    @Test
    public void adds_increased_length_1() {
        assertEquals("100", GradeSchoolMath.add("8", "92"));
    }

    @Test
    public void adds_increased_length_2() {
        assertEquals("150", GradeSchoolMath.add("75", "75"));
    }

    @Test
    public void adds_different_length_1() {
        assertEquals("1145", GradeSchoolMath.add("1070", "75"));
    }

    @Test
    public void adds_different_length_2() {
        assertEquals("1145", GradeSchoolMath.add("75", "1070"));
    }

    @Test
    public void subtracts_same_length_with_carry() {
        assertEquals("15", GradeSchoolMath.sub("90", "75"));
    }

    @Test
    public void subtracts_negative() {
        assertEquals("150", GradeSchoolMath.sub("75", "-75"));
    }

    @Test
    public void subtracts_both_negatives() {
        assertEquals("-100", GradeSchoolMath.sub("-175", "-75"));
    }

    @Test
    public void subtracts_from_zero() {
        assertEquals("-100", GradeSchoolMath.sub("0", "100"));
    }

    @Test
    public void subtracts_zero() {
        assertEquals("100", GradeSchoolMath.sub("100", "0"));
    }

    @Test
    public void subtracts_same_length_without_carry() {
        assertEquals("66", GradeSchoolMath.sub("99", "33"));
    }

    @Test
    public void subtracts_different_length() {
        assertEquals("166", GradeSchoolMath.sub("199", "33"));
    }

    @Test
    public void subtracts_to_negative() {
        assertEquals("-80", GradeSchoolMath.sub("180", "260"));
    }

    @Test
    public void subtracts_to_zero() {
        assertEquals("0", GradeSchoolMath.sub("1800", "1800"));
    }

    @Test
    public void subtracts_to_shorter_number() {
        assertEquals("10", GradeSchoolMath.sub("1800", "1790"));
    }

    @Test
    public void compares_larger() {
        assertEquals(1, GradeSchoolMath.compare("130", "020"));
    }

    @Test
    public void compares_smaller() {
        assertEquals(-1, GradeSchoolMath.compare("10", "88"));
    }

    @Test
    public void compares_smaller_negative() {
        assertEquals(-1, GradeSchoolMath.compare("-90", "88"));
    }

    @Test
    public void compares_larger_both_negatives() {
        assertEquals(1, GradeSchoolMath.compare("-9", "-88"));
    }

    @Test
    public void compares_smaller_both_negatives() {
        assertEquals(-1, GradeSchoolMath.compare("-90", "-88"));
    }

    @Test
    public void compares_equal() {
        assertEquals(0, GradeSchoolMath.compare("1067", "1067"));
    }

    @Test
    public void trims_zeroes_1() {
        assertArrayEquals(new int[]{1,2}, GradeSchoolMath.trimZeroes(new int[]{0,0,1,2}));
    }

    @Test
    public void trims_zeroes_2() {
        assertArrayEquals(new int[]{5}, GradeSchoolMath.trimZeroes(new int[]{0,5}));
    }

    @Test
    public void trims_zeroes_3() {
        assertArrayEquals(new int[]{6}, GradeSchoolMath.trimZeroes(new int[]{6}));
    }

    @Test
    public void trims_zeroes_4() {
        assertArrayEquals(new int[]{0}, GradeSchoolMath.trimZeroes(new int[]{0,0,0}));
    }
}