package br.viniciusjomori.mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import br.viniciusjomori.mockito.Order.Order;
import br.viniciusjomori.mockito.Order.OrderService;

public class OrderServiceTest {

    private final OrderService service = new OrderService();
    private final UUID defaultUuid = UUID.fromString("0ee42ab1-5106-4a4e-a210-530ea34a0f41");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2025, 2, 19, 22, 12);

 
    @Test
    @DisplayName("Mocking static method UUID.randomUUID()")
    void testMockingStaticMethods_UUID_randomUUID() {
        
        try(MockedStatic<UUID> mockedUuid = mockStatic(UUID.class)) {
            // Given
            mockedUuid.when(UUID::randomUUID).thenReturn(defaultUuid);
            
            // When
            Order result = service.createOrder("Foo Bar", 2L, null);

            // Then
            assertEquals(defaultUuid.toString(), result.getId());;
        }
        
    }

    @Test
    @DisplayName("Mocking static method LocalDateTime.now()")
    void testMockingStaticMethods_LocalDateTime_now() {
        
        try(MockedStatic<LocalDateTime> mockedUuid = mockStatic(LocalDateTime.class)) {
            // Given
            mockedUuid.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);
            
            // When
            Order result = service.createOrder("Foo Bar", 2L, null);

            // Then
            assertEquals(defaultLocalDateTime, result.getCreationDate());;
        }
        
    }
}
