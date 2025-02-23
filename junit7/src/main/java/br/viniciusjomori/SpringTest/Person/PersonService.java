package br.viniciusjomori.SpringTest.Person;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.viniciusjomori.SpringTest.App.ResourceNotFoundException;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;

	public List<PersonEntity> findAll() {

		logger.info("Finding all people!");

		return repository.findAll();
	}

	public PersonEntity findById(Long id) {
		
		logger.info("Finding one person!");
		
		return repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public PersonEntity create(PersonEntity person) {

		logger.info("Creating one person!");

		repository.findByEmail(person.getEmail())
			.ifPresent(p -> { 
				throw new RuntimeException("This e-mail alredy exists");
		});
		
		return repository.save(person);
	}
	
	public PersonEntity update(PersonEntity person) {
		
		logger.info("Updating one person!");
		
		var entity = findById(person.getId());

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = findById(id);
		repository.delete(entity);
	}
}
