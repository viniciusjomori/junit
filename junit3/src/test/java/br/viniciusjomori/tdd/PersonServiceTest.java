package br.viniciusjomori.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.viniciusjomori.tdd.Person.IPersonService;
import br.viniciusjomori.tdd.Person.Person;
import br.viniciusjomori.tdd.Person.PersonService;

public class PersonServiceTest {

    Person person;
    IPersonService service;

    @BeforeEach
    void beforeEach() {
        person = new Person("João", "Silva", "Male", "joao.silva@email.com");
        service = new PersonService();
    }
    
    @Test
    @DisplayName("When create a Person, should return a PersonObject")
    void testCreatePerson_WhenSucess_ThenReturnPersonObject() {
        // When
        Person actual = service.createPerson(person);

        // Then
        assertNotNull(actual,
            () -> "The return of createPerson() should not return Null"
        );
    }

    @Test
    @DisplayName("When create a Person succesfully, should return a valid attributes Person")
    void testCreatePerson_WhenSucess_ShouldReturnValidAttibutesPerson() {
        // When
        Person actual = service.createPerson(person);

        // Then
        assertNotEquals(actual.getId(), 0);
        assertEquals(actual.getFirstName(), person.getFirstName());
        assertEquals(actual.getLastName(), person.getLastName());
        assertEquals(actual.getGender(), person.getGender());
        assertEquals(actual.getEmail(), person.getEmail());
    }

    @Test
    @DisplayName("When create a Person with null email, should throw Exception")
    void testCreatePerson_WhenNullEmail_ThenThrowException() {
        
        // Given
        person.setEmail(null);
        String expectedMessage = "Email is Null";
        
        // When & Then
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> service.createPerson(person)
        );
        assertEquals(ex.getMessage(), expectedMessage);
    }
}
