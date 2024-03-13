package cz.spsmb.dao;

import cz.spsmb.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

 public Optional<Person> listByName(String name){

     return find("name", name).singleResultOptional();
 }

    public Person listById(Long id){
        return findById(id);
    }

}