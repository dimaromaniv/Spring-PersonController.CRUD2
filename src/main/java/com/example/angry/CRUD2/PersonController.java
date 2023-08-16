package com.example.angry.CRUD2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonRepository people;

    @RequestMapping(value = "/people",method = RequestMethod.POST)
    ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(people.save(p), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/people/{id}",method = RequestMethod.POST)
    ResponseEntity<Person> getPerson(@PathVariable Long id){
        Person p = people.findById(id).get();
        if(p == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping("/people")
    ResponseEntity<List<Person>> getPersonList(@PathVariable Long id){
        Person personGet = people.findById(id).get();
        if(!people.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(people.findById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/{id}",method = RequestMethod.PUT)
    ResponseEntity<Person> updatePerson(@RequestBody Person p,@PathVariable Long id){
       Person personGet = people.findById(id).get();
       if(personGet == null) {
           return createPerson(p);
       }
        return new ResponseEntity<>(people.save(p),HttpStatus.OK);
    }

    @RequestMapping("/people/{id}")
    ResponseEntity deletePerson(@PathVariable Long id){
        people.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
