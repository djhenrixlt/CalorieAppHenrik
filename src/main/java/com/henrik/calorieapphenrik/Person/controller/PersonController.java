package com.henrik.calorieapphenrik.Person.controller;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import com.henrik.calorieapphenrik.food.service.FoodService;
import com.henrik.calorieapphenrik.food.service.MyFoodListService;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;
    private MyFoodListService myFoodListService;
    private final FoodService foodService;




        @GetMapping("/nav")
    public String loginForm(){
        return "fragments/navbar";
    }


    @GetMapping("/goalCal")
    public ResponseEntity<?> getGoalCalories(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.ok(personService.getGoalCalories(personDto));
    }


    @GetMapping("/main/{id}")
    public String getPersonById(Model model, @PathVariable Long id, @Param("keyword") String keyword) {
        List<FoodDto> foodDtoList = foodService.filter(keyword);
        model.addAttribute("person", personService.findByID(id));
        model.addAttribute("foods",foodDtoList);
        model.addAttribute("keyword", keyword);
        return "myFoodList";
    }




    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("name") String name) {
        personService.deletePerson(name);
    }

}
