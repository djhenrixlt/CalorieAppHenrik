package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.dto.PersonDto;
import com.henrik.calorieapphenrik.food.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;


    @GetMapping("/goalCal")
    public ResponseEntity<?> getGoalCalories(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.ok(personService.getGoalCalories(personDto));
    }

    @GetMapping("/cal/{name}")
    public ResponseEntity<List<?>> getAllCaLByName(@PathVariable String name) {
        return ResponseEntity.ok(personService.getAllCaLByName(name));
    }

    @PostMapping("/new")
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto person) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.savePerson(person));
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("name") String name) {
        personService.deletePerson(name);
    }



}
