package br.viniciusjomori.tdd.Person;

public class PersonService implements IPersonService {

    int id = 1;

    @Override
    public Person createPerson(Person person) {
        
        if (person.getEmail() == null || person.getEmail().isBlank())
            throw new IllegalArgumentException("Email is Null");
        
        
        person.setId(id);
        id++;
        return person;
    }
    
}
