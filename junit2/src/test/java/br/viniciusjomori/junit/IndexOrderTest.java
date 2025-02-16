package br.viniciusjomori.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(4)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IndexOrderTest {
    
    @Test
    @DisplayName("Test A")
    @Order(3)
    void testA() {
        System.out.println("Running Test A");
    }

    @Test
    @DisplayName("Test B")
    @Order(1)
    void testB() {
        System.out.println("Running Test B");
    }

    @Test
    @DisplayName("Test C")
    @Order(2)
    void testC() {
        System.out.println("Running Test C");
    }
}
