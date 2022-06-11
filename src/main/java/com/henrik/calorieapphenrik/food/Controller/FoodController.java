package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;


    @GetMapping("/add")
    public String foodForm(@ModelAttribute(name = "foodDto") FoodDto foodDto){
        return "foodForm";
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("foods",foodService.getAllFoods());
        return "foodList";
    }

//    @GetMapping("/{foodName}")
//    public ResponseEntity<?> getByName(@PathVariable String foodName) {
//        return foodService.getFoodByName(foodName)
//                .map(food -> {
//                    return ResponseEntity
//                            .ok()
//                            .body(food);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/calories")
    public ResponseEntity<Integer> getFoodCalories() {
        return ResponseEntity.ok(foodService.getCalories());
    }


    @PostMapping
    public String create(@ModelAttribute(name = "foodDto") @Valid FoodDto food) {
        foodService.saveFood(food);
        return "redirect:/foods/list";
    }

    @PutMapping("/update/{id}")
    public String update(@ModelAttribute(name = "foodDto") @Valid FoodDto foodDto, @PathVariable("id") Long id) {
       foodService.updateFood(foodDto, id);
       return "editFoodList";
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("name") String name) {
        foodService.deleteFood(name);
    }


}
