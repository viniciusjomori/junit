package br.viniciusjomori.SpringTest.Person;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByEmail(String email);

    @Query("SELECT p from PersonEntity p WHERE firstName = :firstName AND lastName = :lastName")
    Optional<PersonEntity> findByCompleteName(String firstName, String lastName);

}
