package br.viniciusjomori.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

@Order(2)
public class RepeatTest {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
    }
 
    @RepeatedTest(value = 10, name = "{displayName}. {currentRepetition}/{totalRepetitions}")
    @DisplayName("Test dividing by zero")
    void testDiv_When_DividingByZero_ThrowsArithmeticException(
        RepetitionInfo repetitionInfo,
        TestInfo testInfo
    ) {
        System.out.println("Repetition " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
        System.out.println("Running " + testInfo.getTestMethod().get().getName());
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
}

