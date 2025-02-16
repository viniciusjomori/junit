package br.viniciusjomori.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test math operations")
public class SimpleMathTest {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
    }
    
    @Test
    @DisplayName("Test 3 + 5.9 = 8.9")
    void testSum_When_ThreePlusFivePointNine_IsEqualToEightPointNine() {
        // Given
        double n1 = 3D;
        double n2 = 5.9D;
        double expected = 8.9D;

        // When
        double actual = math.sum(n1, n2);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual, 
            () -> n1 + " + " + n2 + "!=" + actual
        );
    }

    @Test
    @DisplayName("Test 1 + 2 = 2 + 1")
    void testSum_When_OnePlusTwo_IsEqualToTwoPlusOne() {
        // Given

        double n1 = 1;
        double n2 = 2;

        double expected = math.sum(n1, n2);

        // When
        double actual = math.sum(n2, n1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test 6.7 - 8 = -1.3")
    void testSub_When_SixPointSevenMinusEight_IsEqualToNegativeOnePointThree() {
        // Given

        double n1 = 6.7D;
        double n2 = 8D;

        double expected = -1.3D;
        double epsilon = 0.0000001;
        
        // When
        double actual = math.subtraction(n1, n2);

        // Then
        assertEquals(expected, actual, epsilon,
            () -> n1 + " - " + n2 + "!=" + actual
        );
        assertTrue(actual < 0);

    }

    @Test
    @DisplayName("Test 2 - 1 <> 1 - 2")
    void testSub_When_TwoMinusOne_IsNotEqualToOneMinusTwo() {
        // Given

        double n1 = 2;
        double n2 = 1;

        double expected = math.subtraction(n1, n2);

        // When
        double actual = math.subtraction(n2, n1);

        // Then
        assertNotEquals(expected, actual);
    }

    @Test
    @DisplayName("Test 3 * 4 = 12")
    void testMulti_When_ThreeTimesFour_IsEqualToTwelve() {
        // Given

        double n1 = 3;
        double n2 = 4;

        double expected = 12;

        // When
        double actual = math.multiplication(n1, n2);

        // Then
        assertEquals(expected, actual,
            () -> n1 + " x " + n2 + "!=" + actual
        );
    }

    @Test
    @DisplayName("Test 2 * 1 = 1 * 2")
    void testMulti_When_TwoTimesOne_IsEqualTo_OneTimesTwo() {
        // Given

        double n1 = 2;
        double n2 = 1;

        double expected = math.multiplication(n1, n2);

        // When
        double actual = math.multiplication(n2, n1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test 12.6 / 3 = 4.2")
    void testDiv_When_TwelvePointSixDivByThree_IsEqualToFourPointTwo() {
        // Given

        double n1 = 12.6D;
        double n2 = 3D;

        double expected = 4.2D;

        // When
        double actual = math.division(n1, n2);

        // Then
        assertEquals(expected, actual,
            () -> n1 + " / " + n2 + "!=" + actual
        );
    }

    @Test
    @DisplayName("Test 2 / 1 <> 1 / 2")
    void testDiv_When_TwoDivByOne_IsNotEqualToOneDivByTwo() {
        // Given

        double n1 = 2;
        double n2 = 1;

        double expected = math.division(n1, n2);

        // When
        double actual = math.division(n2, n1);

        // Then
        assertNotEquals(expected, actual);
    }

    @Test
    @DisplayName("Test dividing by zero")
    void testDiv_When_DividingByZero_ThrowsArithmeticException() {
        // Given

        double n1 = 2;
        double n2 = 0;

        String expectedMessage = "Impossible to divide by zero!";

        // When & Then
        ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
            math.division(n1, n2);
        });

        assertEquals(actual.getMessage(), expectedMessage);
    }

    @Test
    @DisplayName("Test Mean of [9, 1] = 5")
    void testMean_When_MeanOfNinePlusOne_IsEqualToFive() {
        // Given

        double n1 = 9;
        double n2 = 1;

        double expected = 5;
        
        // When
        double actual = math.mean(n1, n2);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test square root of 4 = 2")
    void testSquareRoot_When_SquareRootOfFour_IsEqualToTwo() {
        // Then

        double n = 4;

        double expected = 2;

        // When
        double actual = math.squareRoot(n);

        // Then
        assertEquals(expected, actual);
    }

}

