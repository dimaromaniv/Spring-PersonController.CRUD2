package com.example.angry.CRUD2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class PersonController {
    @Autowired
    PersonRepository people;
    @RequestMapping(value ="/people", method = RequestMethod.POST)
    Person createPerson(@RequestBody Person p){
        return people.save(p);
//        return new  ResponseEntity<>(people.save(p), HttpStatus.CREATED);
    }
    @RequestMapping(value ="/people/{id}", method = RequestMethod.GET)
    Person getPerson(@PathVariable Long id){
//    return new  ResponseEntity<>(people.findById(id).get(), HttpStatus.OK);
        return people.findById(id).get();
    }
    @RequestMapping(value = "/people", method = RequestMethod.GET)
    List<Person> getPersonList(){
        List<Person> personList = new ArrayList<>();
        people.findAll().forEach(personList::add);

        return personList;
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    Person  updatePerson(@RequestBody Person p){
        return people.save(p);
    }
    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    void deletePerson(@PathVariable Long id){
        people.deleteById(id);
    }
}