package br.viniciusjomori.SpringTest.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.viniciusjomori.SpringTest.App.ResourceNotFoundException;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@GetMapping
	public List<PersonEntity> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public PersonEntity findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping()
	public PersonEntity create(@RequestBody PersonEntity person) {
		return service.create(person);
	}
	
	@PutMapping()
	public ResponseEntity<PersonEntity> update(@RequestBody PersonEntity person) {
		try {
			var saved = service.update(person);
			return ResponseEntity.ok(saved);
		} catch(ResourceNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
