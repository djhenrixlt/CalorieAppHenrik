package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import com.henrik.calorieapphenrik.food.service.FoodService;
import com.henrik.calorieapphenrik.food.service.MyFoodListService;
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
    private MyFoodListService myFoodListService;
    private final FoodService foodService;


    @GetMapping("/add")
    public String registerForm(@ModelAttribute(name = "personDto") PersonDto personDto){
        return "registrationForm";
    }
//    @GetMapping("/login")
//    public String loginForm(){
//        return "login";
//    }

//    @GetMapping("/main")
//    public String myFoodList(@ModelAttribute(name = "persons") PersonDto personDto){
//        return "myFoodList";
//    }

        @GetMapping("/nav")
    public String loginForm(){
        return "fragments/navbar";
    }


    @GetMapping("/goalCal")
    public ResponseEntity<?> getGoalCalories(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.ok(personService.getGoalCalories(personDto));
    }
//    @GetMapping("/main")
//    public String getAllConsumed(Model model){
//        model.addAttribute("persons", personService.AllMyList());
//        return "myFoodList";
//    }

    @GetMapping("/main/{id}")
    public String getPersonById(Model model, @PathVariable Long id) {
        model.addAttribute("person", personService.findByID(id));
        model.addAttribute("foods",foodService.getAllFoods());
        return "myFoodList";
    }

    @PostMapping
    public String create(@ModelAttribute(name = "personDto") @Valid PersonDto personDto) {
        personService.savePerson(personDto);
        return "redirect:/persons/login";
    }
    @PostMapping("/delete")
    public String deleteMyFood(@RequestParam Long id){
        myFoodListService.deleteMyFood(id);
        return "redirect:/persons/main/1";
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("name") String name) {
        personService.deletePerson(name);
    }

@PostMapping("/food/{id}")
    public String addToMyList(@PathVariable("id") Long id, @ModelAttribute(name = "food") FoodDto foodDto){
        personService.addToMyList(id,foodDto);
        return "redirect:/persons/main/{id}";
}

}
