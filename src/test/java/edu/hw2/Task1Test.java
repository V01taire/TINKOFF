package edu.hw2;

import edu.hw2.Task1.Expr.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void constantExpressionShouldReturnCorrectValue() {
        Constant constant = new Constant(5);
        assertEquals(5, constant.evaluate());
    }

    @Test
    void negateExpressionShouldReturnNegatedValue() {
        Negate negate = new Negate(new Constant(5));
        assertEquals(-5, negate.evaluate());
    }

    @Test
    void exponentExpressionShouldReturnCorrectValue() {
        Exponent exponent = new Exponent(new Constant(2), 3);
        assertEquals(8, exponent.evaluate());
    }

    @Test
    void exponentExpressionWithZeroBaseAndZeroPowerShouldThrowException() {
        Exponent exponent = new Exponent(new Constant(0), 0);
        assertThrows(ArithmeticException.class, exponent::evaluate);
    }

    @Test
    void additionExpressionShouldReturnSumOfValues() {
        Addition addition = new Addition(new Constant(2), new Constant(3));
        assertEquals(5, addition.evaluate());
    }

    @Test
    void multiplicationExpressionShouldReturnCorrectValue() {
        Multiplication multiplication = new Multiplication(new Constant(2), new Constant(3));
        assertEquals(6, multiplication.evaluate());
    }

    @Test
    void multiplicationExpressionWithNegativeOperandsShouldReturnPositiveResult() {
        Multiplication multiplication = new Multiplication(new Constant(-2), new Constant(3));
        assertEquals(-6, multiplication.evaluate());
    }
}
