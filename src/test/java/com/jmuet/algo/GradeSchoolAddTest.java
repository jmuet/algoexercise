package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradeSchoolAddTest {

    @Test
    public void adds_same_length() {
        assertEquals("145", GradeSchoolAdd.add("70", "75"));
    }

    @Test
    public void adds_different_length() {
        assertEquals("1145", GradeSchoolAdd.add("1070", "75"));
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


}