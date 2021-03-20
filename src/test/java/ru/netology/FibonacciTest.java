package ru.netology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * f(0) = 0
 * f(1) = 1
 * f(2) = f(0) + f(1) = 1
 * f(3) = f(1) + f(2) = 2
 * f(4) = f(2) + f(3) = 3
 * 0, 1, 1, 2, 3, 5, 8, 13, ...
 * */

class FibonacciTest {
    @Test
    @DisplayName("Fibonacci first elements")
    public void testFirstElements() {
        assertEquals(BigInteger.ZERO, Fibonacci.get(0));
        assertEquals(BigInteger.ONE, Fibonacci.get(1));
        assertEquals(BigInteger.ONE, Fibonacci.get(2));
        assertEquals(BigInteger.TWO, Fibonacci.get(3));
    }

    @Test
    @DisplayName("Fibonacci 50th elements")
    public void test50thElements() {
        assertEquals(new BigInteger("12586269025"), Fibonacci.get(50));

    }
}