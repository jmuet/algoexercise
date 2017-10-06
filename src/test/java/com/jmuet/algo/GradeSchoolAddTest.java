package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradeSchoolAddTest {

    @Test
    public void adds_same_length() {
        assertEquals("145", GradeSchoolAdd.add("70", "75"));
    }

    @Test
    public void adds_different_length_1() {
        assertEquals("1145", GradeSchoolAdd.add("1070", "75"));
    }

    @Test
    public void adds_different_length_2() {
        assertEquals("1145", GradeSchoolAdd.add("75", "1070"));
    }

    @Test
    public void subtracts_same_length_with_carry() {
        assertEquals("15", GradeSchoolAdd.sub("90", "75"));
    }

    @Test
    public void subtracts_same_length_without_carry() {
        assertEquals("66", GradeSchoolAdd.sub("99", "33"));
    }

    @Test
    public void subtracts_different_length() {
        assertEquals("166", GradeSchoolAdd.sub("199", "33"));
    }

    @Test
    public void subtracts_to_negative() {
        assertEquals("-80", GradeSchoolAdd.sub("180", "260"));
    }

    @Test
    public void subtracts_to_zero() {
        assertEquals("0", GradeSchoolAdd.sub("1800", "1800"));
    }

    @Test
    public void subtracts_to_shorter_number() {
        assertEquals("10", GradeSchoolAdd.sub("1800", "1790"));
    }

    @Test
    public void compares_larger() {
        assertEquals(1, GradeSchoolAdd.compare("130", "020"));
    }

    @Test
    public void compares_smaller() {
        assertEquals(-1, GradeSchoolAdd.compare("10", "88"));
    }

    @Test
    public void compares_smaller_negative() {
        assertEquals(-1, GradeSchoolAdd.compare("-90", "88"));
    }

    @Test
    public void compares_larger_both_negatives() {
        assertEquals(1, GradeSchoolAdd.compare("-9", "-88"));
    }

    @Test
    public void compares_smaller_both_negatives() {
        assertEquals(-1, GradeSchoolAdd.compare("-90", "-88"));
    }

    @Test
    public void compares_equal() {
        assertEquals(0, GradeSchoolAdd.compare("1067", "1067"));
    }
}