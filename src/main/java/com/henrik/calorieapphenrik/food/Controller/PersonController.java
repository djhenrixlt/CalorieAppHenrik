package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import com.henrik.calorieapphenrik.food.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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


    @GetMapping("/add")
    public String registerForm(@ModelAttribute(name = "personDto") PersonDto personDto){
        return "registrationForm";
    }
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

//    @GetMapping("/main")
//    public String myFoodList(@ModelAttribute(name = "persons") PersonDto personDto){
//        return "myFoodList";
//    }


    @GetMapping("/goalCal")
    public ResponseEntity<?> getGoalCalories(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.ok(personService.getGoalCalories(personDto));
    }
    @GetMapping("/main")
    public String getAllConsumed(Model model, @ModelAttribute(name = "goal") PersonDto personDto){
        model.addAttribute("persons", personService.AllMyList());
        return "myFoodList";
    }

    @GetMapping("/cal/{name}")
    public ResponseEntity<List<?>> getAllCaLByName(@PathVariable String name) {
        return ResponseEntity.ok(personService.getAllCaLByName(name));
    }

    @PostMapping
    public String create(@ModelAttribute(name = "personDto") @Valid PersonDto personDto) {
        personService.savePerson(personDto);
        return "redirect:/persons/login";
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("name") String name) {
        personService.deletePerson(name);
    }

@PostMapping("/food/{id}")
    public String addToMyList(@PathVariable("id") Long id, @ModelAttribute(name = "food") FoodDto foodDto){
        personService.addToMyList(id,foodDto);
        return "redirect:/persons/main";
}

}
