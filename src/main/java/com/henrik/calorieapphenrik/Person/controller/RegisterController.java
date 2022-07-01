package com.henrik.calorieapphenrik.Person.controller;

import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.savePerson(personDto));
    }
}
