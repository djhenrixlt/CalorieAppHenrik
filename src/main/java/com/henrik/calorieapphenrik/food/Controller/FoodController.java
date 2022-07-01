package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getAllFilter(@Param("keyword") String keyword, Principal principal) {
        List<FoodDto> foodDtoList = foodService.filter(keyword);
        return ResponseEntity.ok(foodDtoList);
    }


    @GetMapping("{name}")
    public String getByName(Model model, @PathVariable("name") String name) {
        model.addAttribute("foodDto", foodService.getFoodByName(name));
        return "foodForm";

    }

    @GetMapping("/calories")
    public ResponseEntity<Integer> getFoodCalories() {
        return ResponseEntity.ok(foodService.getCalories());
    }


    @PutMapping("/new")
    public ResponseEntity<FoodDto> create(@RequestBody @Valid FoodDto food, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodService.saveFood(food, principal.getName()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FoodDto> update(@RequestBody @Valid FoodDto foodDto, @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(foodService.updateFood(foodDto, id));
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String name, Principal principal) {
        foodService.deleteFood(name, principal.getName());
    }


}
