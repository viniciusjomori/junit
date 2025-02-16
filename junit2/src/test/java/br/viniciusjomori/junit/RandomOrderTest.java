package br.viniciusjomori.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(3)
@TestMethodOrder(MethodOrderer.Random.class)
public class RandomOrderTest {
    
    @Test
    @DisplayName("Test A")
    void testA() {
        System.out.println("Running Test A");
    }

    @Test
    @DisplayName("Test B")
    void testB() {
        System.out.println("Running Test B");
    }

    @Test
    @DisplayName("Test C")
    void testC() {
        System.out.println("Running Test C");
    }
}
