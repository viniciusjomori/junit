package br.viniciusjomori.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListMockTest {
    
    @Test
    @DisplayName("list.size() should return 10")
    void testMockList_When_SizeIsCalled_ShouldReturn10() {
        // Given
        List<?> list = mock(List.class);
        int expected = 10;
        when(list.size()).thenReturn(expected);

        // When
        int actual = list.size();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("calling list.size() multiple times should return multiple values")
    void testMockList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given
        List<?> list = mock(List.class);
        int expected1 = 10;
        int expected2 = 20;
        int expected3 = 30;
        when(list.size())
            .thenReturn(expected1)
            .thenReturn(expected2)
            .thenReturn(expected3);

        // When & Then
        assertEquals(expected1, list.size());
        assertEquals(expected2, list.size());
        assertEquals(expected3, list.size());
    }

    @Test
    @DisplayName("list.get() should return String")
    void testMockList_When_GetIsCalled_ShoudReturnString() {
        // Given
        var list = mock(List.class);
        when(list.get(anyInt())).thenReturn("any string");

        // When & Then
        assertEquals("any string", list.get(anyInt()));
    }

    @Test
    @DisplayName("list.size() should throws exception")
    void testMockList_When_GetIsCalled_ShouldThrowsException() {
        // Given
        var list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo bar"));

        // When & Then
        var ex = assertThrows(RuntimeException.class, () -> {
            list.get(anyInt());
        });
        assertEquals("Foo bar", ex.getMessage());
    }
}
