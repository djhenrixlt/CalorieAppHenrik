package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.Person.Repository.PersonRepo;
import com.henrik.calorieapphenrik.Person.dto.PersonDto;
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
    private final PersonRepo personRepo;
    private final PersonService personService;
    private FoodService foodService;


    public void deleteMyFood(Long foodId, Long personID) {
        PersonDto personDto = personService.findByID(personID);
        Optional<MyList> food = myListRepo.findById(foodId);
        if (!myListRepo.existsById(foodId)) {
            throw new FoodException("id not exist" + foodId);
        }
        setGoals(personDto, personDto.getCaloriesConsumed() - food.get().getCalories());
        Calories calories = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        Calories save = PersonMapper.PERSON_MAPPER.mapForUpdate(personDto, calories);
        personRepo.save(save);
        personDto.deleteFromMyList(food.get());

        Calories calories2 = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        personRepo.save(calories2);
    }


    public @NonNull Set<MyList> addToMyList(Long id, String name) {
        Optional<FoodDto> foodDto = foodService.getFoodByName(name);
        PersonDto personDto = personService.findByID(id);
        setGoals(personDto, personDto.getCaloriesConsumed() + foodDto.get().getCalories());
        Calories calories = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        Calories save = PersonMapper.PERSON_MAPPER.mapForUpdate(personDto, calories);
        personRepo.save(save);
        personDto.addToMyLIst(foodDto.get());
        Calories saveList = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        personRepo.save(saveList);
        return personDto.getMyFoodList();
    }

    private void setGoals(PersonDto personDto, int foodCalories) {
        personDto.setCaloriesConsumed(foodCalories);
        personDto.setCaloriesLeft(personDto.getGoalCalories() - personDto.getCaloriesConsumed());
    }

}
