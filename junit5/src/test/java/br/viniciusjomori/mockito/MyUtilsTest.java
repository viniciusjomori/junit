package br.viniciusjomori.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import br.viniciusjomori.mockito.Utils.MyUtils;

public class MyUtilsTest {
    
    @Test
    @DisplayName("Mocking static method MyUtils.getWelcomeMessage()")
    void testMockingStaticMethodsWithParams() {
        try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            // Given
            mockedStatic.when(() -> MyUtils.getWelcomeMessage(eq("username"), anyBoolean()))
                .thenReturn("Dear / Hello");

            // When
            String result = MyUtils.getWelcomeMessage("username", false);
            
            // Then
            assertEquals("Dear / Hello", result);
        }
        

    }
}
