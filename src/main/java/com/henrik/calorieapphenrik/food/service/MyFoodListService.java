package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.food.Exception.FoodException;
import com.henrik.calorieapphenrik.food.mapper.MyListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class MyFoodListService {

    private final MyListRepo myListRepo;

    public void deleteMyFood(Long id) {
        Optional<MyList> food = myListRepo.findById(id);
        if (!myListRepo.existsById(id)) {
            throw new FoodException("id not exist" + id);
        }
        myListRepo.delete(food.get());
    }

}
