package com.jmuet.algo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KaratsubaTest {

    @Test
    public void multiplies_single_digits() {
        assertEquals("24", Karatsuba.multiply("4", "6"));
    }

    @Test
    public void multiplies_by_one() {
        assertEquals("24", Karatsuba.multiply("24", "1"));
    }

    @Test
    public void multiplies_by_zero() {
        assertEquals("0", Karatsuba.multiply("240", "0"));
    }

    @Test
    public void multiplies_double_digits() {
        assertEquals("625", Karatsuba.multiply("25", "25"));
    }

    @Test
    public void multiplies_double_digits_2() {
        assertEquals("150", Karatsuba.multiply("15", "10"));
    }

    @Test
    public void multiplies_double_digits_3() {
        assertEquals("150", Karatsuba.multiply("10", "15"));
    }

    @Test
    public void multiplies_double_digits_4() {
        assertEquals("580", Karatsuba.multiply("58", "10"));
    }

    @Test
    public void multiplies_quad_digits() {
        assertEquals("1048576", Karatsuba.multiply("1024", "1024"));
    }

    @Test
    public void multiplies_quad_digits_2() {
        assertEquals("1048000", Karatsuba.multiply("1048", "1000"));
    }

    @Test
    public void multiplies_8_digits() {
        assertEquals("563767409744880", Karatsuba.multiply("12349898", "45649560"));
    }

    @Test
    public void multiplies_8_digits_2() {
        assertEquals("123498980000000", Karatsuba.multiply("12349898", "10000000"));
    }

    @Test
    public void multiplies_8_digits_3() {
        assertEquals("400000000000000", Karatsuba.multiply("20000000", "20000000"));
    }

    @Test
    public void challenge() {
        assertEquals("8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184", Karatsuba.multiply("3141592653589793238462643383279502884197169399375105820974944592",
                "2718281828459045235360287471352662497757247093699959574966967627"));
    }

}