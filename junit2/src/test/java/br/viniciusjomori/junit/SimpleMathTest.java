package br.viniciusjomori.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Test math operations")
@Order(1)
public class SimpleMathTest {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        math = new SimpleMath();
    }
    
    @DisplayName("Test Double Division - MethodSource")
    @ParameterizedTest
    @MethodSource("testDivisionInputParameters")
    void methodSourceTestDiv_When_TwelvePointSixDivByThree_IsEqualToFourPointTwo(double n1, double n2, double expected) {

        // When
        double actual = math.division(n1, n2);

        // Then
        assertEquals(expected, actual,
            () -> n1 + " / " + n2 + "!=" + actual
        );
    }

    @DisplayName("Test Double Division - CsvFileSource")
    @ParameterizedTest
    @CsvFileSource(resources = "/testDivision.csv")
    void csvTestDiv_When_TwelvePointSixDivByThree_IsEqualToFourPointTwo(double n1, double n2, double expected) {

        // When
        double actual = math.division(n1, n2);

        // Then
        assertEquals(expected, actual, 2D,
            () -> n1 + " / " + n2 + "!=" + actual
        );
    }

    static Stream<Arguments> testDivisionInputParameters() {
        return Stream.of(
            Arguments.of(12.6D, 3D, 4.2D),
            Arguments.of(14.2D, 2D, 7.1D),
            Arguments.of(16.8D, 4D, 4.2D)
        );
    }

}

