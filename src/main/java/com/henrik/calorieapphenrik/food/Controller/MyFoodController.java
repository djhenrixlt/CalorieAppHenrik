package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.service.MyFoodListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/myList")
public class MyFoodController {

    private final MyFoodListService myFoodListService;


    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyFood(@RequestParam Long foodId, @PathVariable("id") Long id) {
        myFoodListService.deleteMyFood(foodId, id);
    }

    @PostMapping("/food/{id}")
    public ResponseEntity<?> addToMyList(@PathVariable("id") Long id, @RequestParam String name) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(myFoodListService.addToMyList(id, name));
    }

}
