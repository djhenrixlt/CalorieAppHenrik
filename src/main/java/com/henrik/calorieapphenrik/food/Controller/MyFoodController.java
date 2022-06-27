package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.service.MyFoodListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/myList")
public class MyFoodController {

    private final MyFoodListService myFoodListService;


    @PostMapping("/delete/{id}")
    public String deleteMyFood(@RequestParam Long foodId, @PathVariable("id") Long id) {
        myFoodListService.deleteMyFood(foodId, id);
        return "redirect:/persons/main/?hex=personFood";
    }

    @PostMapping("/food/{id}")
    public String addToMyList(@PathVariable("id") Long id, @RequestParam String name) {
        myFoodListService.addToMyList(id, name);
        return "redirect:/persons/main/?hex=personFood";
    }

}
