package br.viniciusjomori.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpyTest {

    @Test
    @DisplayName("Testing mockito spy")
    void test() {
        // Given
        List<String> arrayList = spy(new ArrayList<>());
        when(arrayList.size()).thenReturn(5);
        arrayList.add("Foo Bar");

        // When & Then
        assertEquals(5, arrayList.size());

    }

}
