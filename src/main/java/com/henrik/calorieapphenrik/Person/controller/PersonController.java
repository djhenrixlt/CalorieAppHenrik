package com.henrik.calorieapphenrik.Person.controller;

import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;


    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findByID(id));
    }


//    @GetMapping("/goalCal")
//    public ResponseEntity<?> getGoalCalories(@RequestBody @Valid PersonDto personDto) {
//        return ResponseEntity.ok(personService.getGoalCalories(personDto));
//    }


    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("name") String name) {
        personService.deletePerson(name);
    }

}
