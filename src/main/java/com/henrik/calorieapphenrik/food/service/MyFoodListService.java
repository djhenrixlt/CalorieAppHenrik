package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.Person.Repository.CaloriesRepo;
import com.henrik.calorieapphenrik.Person.dto.CaloriesDto;
import com.henrik.calorieapphenrik.Person.entity.Calories;
import com.henrik.calorieapphenrik.Person.mapper.PersonMapper;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.food.Exception.FoodException;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.mapper.MyListRepo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class MyFoodListService {

    private final MyListRepo myListRepo;
    private final CaloriesRepo caloriesRepo;
    private final PersonService personService;
    private FoodService foodService;


    public void deleteMyFood(Long foodId, Long personID) {
        CaloriesDto caloriesDto = personService.findByID(personID);
        Optional<MyList> food = myListRepo.findById(foodId);
        if (!myListRepo.existsById(foodId)) {
            throw new FoodException("id not exist" + foodId);
        }
        setGoals(caloriesDto, caloriesDto.getCaloriesConsumed() - food.get().getCalories());
        Calories calories = PersonMapper.PERSON_MAPPER.mapModel(caloriesDto);
        Calories save = PersonMapper.PERSON_MAPPER.mapForUpdate(caloriesDto, calories);
        caloriesRepo.save(save);
        caloriesDto.deleteFromMyList(food.get());

        Calories calories2 = PersonMapper.PERSON_MAPPER.mapModel(caloriesDto);
        caloriesRepo.save(calories2);
    }


    public @NonNull Set<MyList> addToMyList(Long id, String name) {
        Optional<FoodDto> foodDto = foodService.getFoodByName(name);
        CaloriesDto caloriesDto = personService.findByID(id);
        setGoals(caloriesDto, caloriesDto.getCaloriesConsumed() + foodDto.get().getCalories());
        Calories calories = PersonMapper.PERSON_MAPPER.mapModel(caloriesDto);
        Calories save = PersonMapper.PERSON_MAPPER.mapForUpdate(caloriesDto, calories);
        caloriesRepo.save(save);
        caloriesDto.addToMyLIst(foodDto.get());
        Calories saveList = PersonMapper.PERSON_MAPPER.mapModel(caloriesDto);
        caloriesRepo.save(saveList);
        return caloriesDto.getMyFoodList();
    }

    private void setGoals(CaloriesDto caloriesDto, int foodCalories) {
        caloriesDto.setCaloriesConsumed(foodCalories);
        caloriesDto.setCaloriesLeft(caloriesDto.getGoalCalories() - caloriesDto.getCaloriesConsumed());
    }

}
