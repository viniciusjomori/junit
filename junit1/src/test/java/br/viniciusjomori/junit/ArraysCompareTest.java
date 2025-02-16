package br.viniciusjomori.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ArraysCompareTest {
 
    @Test
    @DisplayName("Test sort array")
    @Timeout(value = 15, unit = TimeUnit.MILLISECONDS)
    void test() {
        // Given
        int[] array = {25, 8, 21, 32, 3};
        int[] expectedArray = {3, 8, 21, 25, 32};

        // When
        Arrays.sort(array);

        // Then
        assertArrayEquals(expectedArray, array);
    }    
}

