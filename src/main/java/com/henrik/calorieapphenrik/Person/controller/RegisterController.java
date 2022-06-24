package com.henrik.calorieapphenrik.Person.controller;

import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import com.henrik.calorieapphenrik.food.service.FoodService;
import com.henrik.calorieapphenrik.food.service.MyFoodListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private PersonService personService;

    @GetMapping
    public String registerForm(@ModelAttribute(name = "personDto") PersonDto personDto){
        return "registrationForm";
    }

    @PostMapping
    public String create(@ModelAttribute(name = "personDto") @Valid PersonDto personDto) {
        personService.savePerson(personDto);
        return "redirect:/persons/login";
    }
}
