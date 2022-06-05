package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.service.FoodService;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<FoodDto>> getAll(){
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @GetMapping("/{foodName}")
    public ResponseEntity<?> getByName(@PathVariable String foodName){
        return foodService.getFoodByName(foodName)
                .map(food -> {
                    return ResponseEntity
                            .ok()
                            .body(food);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/calories")
    public ResponseEntity<Integer> getFoodCalories(){
        return ResponseEntity.ok(foodService.getCalories());
    }



    @PostMapping("/new")
    public ResponseEntity<FoodDto> create(@RequestBody @Valid FoodDto food){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodService.saveFood(food));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FoodDto> update(@RequestBody @Valid FoodDto food, @PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(foodService.updateFood(food, id));
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("name") String name) throws Exception {
        foodService.deleteFood(name);
    }




}
