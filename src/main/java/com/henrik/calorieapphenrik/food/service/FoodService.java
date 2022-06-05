package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.Exception.FoodException;
import com.henrik.calorieapphenrik.food.Repository.FoodRepo;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.mapper.FoodMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodRepo foodRepo;

    public List<FoodDto> getAllFoods() {
        return foodRepo.findAll()
                .stream()
                .map(FoodMapper.FOOD_MAPPER::mapDto)
                .toList();
    }

    public Optional<FoodDto> getFoodByName(String foodName) {
        Optional<Food> food = foodRepo.findByName(foodName);
        if (food.isEmpty()){
            throw new FoodException("Food Not Exist");
        }
        return Optional.of(FoodMapper.FOOD_MAPPER.mapDto(food.get()));

    }
    public FoodDto updateFood(FoodDto foodDto, Long id) {
        boolean  isExist = foodRepo.existsById(id);
        if (!isExist){
            throw new FoodException("Food not exist");
        }
        Food update =  FoodMapper.FOOD_MAPPER.mapModel(foodDto);
        foodRepo.save(update);
        return FoodMapper.FOOD_MAPPER.mapDto(update);
    }

    public FoodDto saveFood(FoodDto food) {
        Food saveFood = foodRepo.save(FoodMapper.FOOD_MAPPER.mapModel(food));
        return FoodMapper.FOOD_MAPPER.mapDto(saveFood);
    }

    public void deleteFood(String name) {
        Optional<Food> food = foodRepo.findByName(name.toLowerCase());
       Long id = food.get().getId();
        if (!foodRepo.existsById(id)) {
            throw new FoodException("Food not exist");
        }
        foodRepo.delete(food.get());
    }
    public Integer getCalories(){
        return foodRepo.getCaloriesSUm();
    }

}
