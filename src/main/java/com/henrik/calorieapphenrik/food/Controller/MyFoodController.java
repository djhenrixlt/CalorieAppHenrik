package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.service.FoodService;
import com.henrik.calorieapphenrik.food.service.MyFoodListService;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/myList")
public class MyFoodController {

    private PersonService personService;
    private MyFoodListService myFoodListService;
    private final FoodService foodService;

    @PostMapping("/delete/{id}")
    public String deleteMyFood(@RequestParam Long foodId, @PathVariable("id") Long id){
        myFoodListService.deleteMyFood(foodId,id);
        return "redirect:/persons/main/?hex=personFood";
    }

    @PostMapping("/food/{id}")
    public String addToMyList(@PathVariable("id") Long id, @ModelAttribute(name = "food") FoodDto foodDto){
        myFoodListService.addToMyList(id,foodDto);
        return "redirect:/persons/main/?hex=personFood";
    }

}
