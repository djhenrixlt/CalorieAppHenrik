package com.henrik.calorieapphenrik.Person.controller;

import com.henrik.calorieapphenrik.Person.dto.CaloriesDto;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;


    @GetMapping("/person/{id}")
    public ResponseEntity<CaloriesDto> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findByID(id));
    }


//    @GetMapping("/goalCal")
//    public ResponseEntity<?> getGoalCalories(@RequestBody @Valid CaloriesDto personDto) {
//        return ResponseEntity.ok(personService.getGoalCalories(personDto));
//    }


    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("name") String name) {
        personService.deletePerson(name);
    }

}
