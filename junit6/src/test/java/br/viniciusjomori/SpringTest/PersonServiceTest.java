package br.viniciusjomori.SpringTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.viniciusjomori.SpringTest.App.ResourceNotFoundException;
import br.viniciusjomori.SpringTest.Person.PersonEntity;
import br.viniciusjomori.SpringTest.Person.PersonRepository;
import br.viniciusjomori.SpringTest.Person.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;
 
    @InjectMocks
    private PersonService service;

    private PersonEntity person1;

    @BeforeEach
    public void setup() {
        person1 = PersonEntity.builder()
            .firstName("firstName")
            .lastName("lastName")
            .address("address")
            .gender("MALE")
            .email("test@email.com")
            .build();
    }

    @Test
    @DisplayName("Save person should return saved object")
    void testPersonService_WhenCreate_ThenReturnSavedObject() {
        // Given
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(repository.save(person1)).thenReturn(person1);
        
        // When
        PersonEntity saved = service.create(person1);

        // Then
        assertNotNull(saved);
        assertEquals(person1.getEmail(), saved.getEmail());
    }

    @Test
    @DisplayName("Save person should throw exception")
    void testPersonService_WhenCreateExistingEmail_ThenThrowException() {
        // Given
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(person1));
        
        // When & Then
        assertThrows(RuntimeException.class, () -> {
            service.create(person1);
        });
        verify(repository, never()).save(person1);
    }

    @Test
    @DisplayName("findAll() should return person list")
    void testPersonService_WhenFindAll_ThenReturnPersonList() {
        // Given
        PersonEntity person2 = PersonEntity.builder()
           .firstName("firstName2")
           .lastName("lastName2")
           .address("address2")
           .gender("MALE")
           .email("test2@email.com")
           .build();

        when(repository.findAll()).thenReturn(List.of(person1, person2));
        
        // When
        List<PersonEntity> personList = repository.findAll();

        // Then
        assertEquals(2, personList.size());
    }

    @Test
    @DisplayName("findAll() should return empty list")
    void testPersonService_WhenFindAll_ThenEmptyList() {
        // Given
        when(repository.findAll()).thenReturn(Collections.emptyList());
        
        // When
        List<PersonEntity> personList = repository.findAll();

        // Then
        assertEquals(0, personList.size());
    }

    @Test
    @DisplayName("findById() should return correct person")
    void testPersonService_WhenFindById_ThenReturnCorrectObject() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(person1));
        
        // When
        PersonEntity saved = service.findById(1L);

        // Then
        assertNotNull(saved);
        assertEquals(person1.getEmail(), saved.getEmail());
    }

    @Test
    @DisplayName("findById() should throw exception")
    void testPersonService_WhenFindById_ThenThrowException() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        
        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(1L);
        });
    }

    @Test
    @DisplayName("Update person should return saved object")
    void testPersonService_WhenUpdated_ThenReturnSavedObject() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(person1));
        when(repository.save(person1)).thenReturn(person1);
        
        person1.setId(1L);
        person1.setFirstName("firstName2");
        person1.setLastName("lastName2");

        // When
        PersonEntity saved = service.update(person1);

        // Then
        assertNotNull(saved);
        assertEquals(person1.getFirstName(), saved.getFirstName());
        assertEquals(person1.getLastName(), saved.getLastName());
        assertEquals(person1.getEmail(), saved.getEmail());
    }

    @Test
    @DisplayName("Delete person should do nothing")
    void testPersonService_WhenDelete_ThenDoNothingt() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(person1));
        doNothing().when(repository).delete(person1);

        // When
        service.delete(1L);

        // Then
        verify(repository).delete(person1);
    }


}
