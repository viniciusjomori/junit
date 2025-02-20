package br.viniciusjomori.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import br.viniciusjomori.mockito.Pay.CheckoutService;
import br.viniciusjomori.mockito.Pay.PaymentProcessor;

public class PaymentProcessorTest {

    @BeforeEach
    public void setup() {

    }

    @DisplayName("Testing static method with params PaymentProcessor.purchaseProduct('product', '101')")
    @Test
    public void testMockingObjectConstruction_PaymentProcessor_purchaseProduct() {
        // Given
        try (MockedConstruction<PaymentProcessor> mockedConstruction = mockConstruction(
            PaymentProcessor.class,
            (mock, context) -> {
                when(mock.chargeCustomer(anyString(), any(BigDecimal.class)))
                    .thenReturn(BigDecimal.TEN);
            }
        )) {

            // When
            CheckoutService service = new CheckoutService();
            BigDecimal result = service.purchaseProduct("product", "101");

            // Then
            assertEquals(BigDecimal.TEN, result);
            assertEquals(1, mockedConstruction.constructed().size());


        }
    }
}
