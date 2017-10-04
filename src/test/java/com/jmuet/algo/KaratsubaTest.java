package com.jmuet.algo;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class KaratsubaTest {

    @Test
    public void multiplies_single_digits() {
        assertEquals("24", Karatsuba.multiply("4", "6"));
    }

    @Test
    public void multiplies_double_digits() {
        assertEquals("625", Karatsuba.multiply("25", "25"));
    }

    @Test
    public void rejects_x_input_length_not_power_of_two() {
        try {
            Karatsuba.multiply("123", "243");
            fail("should have thrown exception");
        } catch (IllegalArgumentException e) {
            assertEquals("input length not power of 2", e.getMessage());
        }
    }

    @Test
    public void rejects_input_with_differing_length() {
        try {
            Karatsuba.multiply("1235", "247");
            fail("should have thrown exception");
        } catch (IllegalArgumentException e) {
            assertEquals("input lengths must be the same", e.getMessage());
        }
    }

}