package br.viniciusjomori.SpringTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.viniciusjomori.SpringTest.Person.PersonEntity;
import br.viniciusjomori.SpringTest.Person.PersonRepository;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

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
    void testPersonRepository_WhenSave_ThenReturnSavedObject() {
        // When
        PersonEntity saved = repository.save(person1);

        // Then
        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
        assertEquals(person1.getEmail(), saved.getEmail());
    }

    @Test
    @DisplayName("findAll() should list all saved objects")
    void testPersonRepository_WhenFindAll_TheReturnPersonList() {
        // Given        
        PersonEntity person2 = PersonEntity.builder()
           .firstName("firstName2")
           .lastName("lastName2")
           .address("address2")
           .gender("MALE")
           .email("test2@email.com")
           .build();
        
        repository.save(person1);
        repository.save(person2);
        
        // When
        List<PersonEntity> personList = repository.findAll();
        
        // Then
        assertEquals(2, personList.size());
    }

    @Test
    @DisplayName("findById() should return correct person")
    void testPersonRepository_WhenFindById_ThenReturnCorrectObject() {
        // Given
        repository.save(person1);
        
        // When
        PersonEntity saved = repository.findById(person1.getId()).get();

        // Then
        assertEquals(person1.getId(), saved.getId());
        assertEquals(person1.getFirstName(), saved.getFirstName());
    }

    @Test
    @DisplayName("findByEmail() should return correct person")
    void testPersonRepository_WhenFindByEmail_ThenReturnCorrectObject() {
        // Given
        repository.save(person1);
        
        // When
        PersonEntity saved = repository.findByEmail(person1.getEmail()).get();

        // Then
        assertEquals(person1.getId(), saved.getId());
        assertEquals(person1.getEmail(), saved.getEmail());
    }

    @Test
    @DisplayName("findByCompleteName() should return correct person")
    void testPersonRepository_WhenFindByCompleteName_ThenReturnCorrectObject() {
        // Given
        repository.save(person1);
        
        // When
        PersonEntity saved = repository.findByCompleteName(person1.getFirstName(), person1.getLastName()).get();

        // Then
        assertEquals(person1.getFirstName(), saved.getFirstName());
        assertEquals(person1.getLastName(), saved.getLastName());;
    }

    @Test
    @DisplayName("updated should return saved object")
    void testPersonRepository_WhenUpdate_ThenReturnSavedObject() {
        // Given
        repository.save(person1);
        
        // When
        person1.setFirstName("firstName_updated");
        person1.setLastName("lastName_updated");
        PersonEntity saved = repository.save(person1);

        // Then
        assertEquals("firstName_updated", saved.getFirstName());
        assertEquals("lastName_updated", saved.getLastName());
    }

    @Test
    @DisplayName("deleteById() should delete correct person")
    void testPersonRepository_WhenDeleteById_ThenDeleteObject() {
        // Given
        repository.save(person1);
        
        // When
        repository.deleteById(person1.getId());
        Optional<PersonEntity> optional = repository.findById(person1.getId());

        // Then
        assertFalse(optional.isPresent());

    }
}
