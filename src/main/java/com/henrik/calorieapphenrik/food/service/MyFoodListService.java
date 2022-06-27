package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.Person.service.PersonService;
import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.Person.entity.Person;
import com.henrik.calorieapphenrik.food.Exception.FoodException;
import com.henrik.calorieapphenrik.Person.Repository.PersonRepo;
import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import com.henrik.calorieapphenrik.food.Repository.FoodRepo;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.mapper.MyListRepo;
import com.henrik.calorieapphenrik.Person.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class MyFoodListService {

    private final MyListRepo myListRepo;
    private final PersonRepo personRepo;
    private final PersonService personService;
   private FoodService foodService;


    public void deleteMyFood( Long foodId, Long personID) {
        PersonDto personDto = personService.findByID(personID);
        Optional<MyList> food = myListRepo.findById(foodId);
        if (!myListRepo.existsById(foodId)) {
            throw new FoodException("id not exist" + foodId);
        }
        setGoals(personDto, personDto.getCaloriesConsumed() - food.get().getCalories());
        Person person = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        Person save = PersonMapper.PERSON_MAPPER.mapForUpdate(personDto, person);
        personRepo.save(save);
        personDto.deleteFromMyList(food.get());

        Person person2 = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        personRepo.save(person2);
    }



    public void addToMyList(Long id, FoodDto foodDto2){
        Optional<FoodDto> foodDto = foodService.getFoodByName(foodDto2.getName());
        PersonDto personDto = personService.findByID(id);
        setGoals(personDto, personDto.getCaloriesConsumed() + foodDto.get().getCalories());
        Person person = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        Person save = PersonMapper.PERSON_MAPPER.mapForUpdate(personDto, person);
        personRepo.save(save);
        personDto.addToMyLIst(foodDto.get());
        Person saveList = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        personRepo.save(saveList);
    }

    private void setGoals(PersonDto personDto, int foodCalories) {
        personDto.setCaloriesConsumed(foodCalories);
        personDto.setCaloriesLeft(personDto.getGoalCalories()- personDto.getCaloriesConsumed());
    }

}
