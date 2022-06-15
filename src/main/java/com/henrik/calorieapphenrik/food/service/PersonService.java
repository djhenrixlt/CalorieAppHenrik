package com.henrik.calorieapphenrik.food.service;


import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.food.Entity.Person;
import com.henrik.calorieapphenrik.food.Exception.FoodException;
import com.henrik.calorieapphenrik.food.Exception.PersonException;
import com.henrik.calorieapphenrik.food.Repository.FoodRepo;
import com.henrik.calorieapphenrik.food.Repository.PersonRepo;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import com.henrik.calorieapphenrik.food.mapper.FoodMapper;
import com.henrik.calorieapphenrik.food.mapper.MyListRepo;
import com.henrik.calorieapphenrik.food.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {

    private static final String WOMAN = "woman";
    private static final String MEN = "men";
    private static final String SEDENTARY = "sedentary";
    private static final String LIGHTLY = "lightly";
    private static final String MODERATELY = "moderately";
    private static final String ACTIVE = "active";
    private static final String VERY = "very";
    private FoodRepo foodRepo;
    private PersonRepo personRepo;
    private FoodService foodService;
    private MyListRepo myListRepo;

    //Gets calories just only by value do not save data
    public Integer getGoalCalories(PersonDto personDto) {
        return countBmr(personDto).intValue();
    }

    public PersonDto savePerson(PersonDto personDto) {
        Person person = PersonMapper.PERSON_MAPPER.mapModelSavePerson(personDto);
        setGoals(personDto, person);
        personRepo.save(person);
        return PersonMapper.PERSON_MAPPER.mapDtoForSavePerson(person);
    }

    public List<Integer> getAllCaLByName(String name) {
        Person person = personRepo.findByEmail(name);
        if (isEquals(person.getFirstName(), name)) {
            throw new PersonException("person not Exist" + name);
        }
        return List.of(person.getGoalCalories(), person.getCaloriesConsumed(), person.getCaloriesLeft());
    }

    public void deletePerson(String name) {
        Person person = personRepo.findByEmail(name);
        Long id = person.getId();
        if (!personRepo.existsById(id)) {
            throw new PersonException("Name not exist");
        }
        personRepo.delete(person);
    }

//    public void addToMyList(Long id, FoodDto foodDto2){
//        Optional<FoodDto> foodDto = foodService.getFoodByName(foodDto2.getName());
//        PersonDto personDto = findByID(id);
//        personDto.setCaloriesConsumed(personDto.getCaloriesConsumed()+ foodDto.get().getCalories());
//        personDto.setCaloriesLeft(personDto.getGoalCalories()-personDto.getCaloriesConsumed());
//        List<FoodDto> myFoodList = new LinkedList<>();
//        myFoodList.add(foodDto.get());
//        personDto.setMyFoodList(myFoodList);
//        Person person = PersonMapper.PERSON_MAPPER.mapModelSavePerson(personDto);
//        Person save = PersonMapper.PERSON_MAPPER.mapForUpdate(personDto, person);
//        personRepo.save(save);
//    }

    public void addToMyList(Long id, FoodDto foodDto2){
        Optional<FoodDto> foodDto = foodService.getFoodByName(foodDto2.getName());
        PersonDto personDto = findByID(id);
        personDto.setCaloriesConsumed(personDto.getCaloriesConsumed()+ foodDto.get().getCalories());
        personDto.setCaloriesLeft(personDto.getGoalCalories()-personDto.getCaloriesConsumed());
        Person person = PersonMapper.PERSON_MAPPER.mapModelSavePerson(personDto);
        Person save = PersonMapper.PERSON_MAPPER.mapForUpdate(personDto, person);
        personRepo.save(save);
        MyList myList = FoodMapper.FOOD_MAPPER.mapToListModel(foodDto.get());
        myListRepo.save(myList);
    }
    public List<MyList> AllMyList(){
        return myListRepo.findAll();
    }



    public PersonDto findByID(Long id){
        Optional<Person> person = personRepo.findById(id);
        return PersonMapper.PERSON_MAPPER.mapDtoForSavePerson(person.get());
    }
//    public void addToLIstByFoodName(String name, PersonDto personDto){
//        this.getFoodList(name,personDto.getFoodList())
//                .ifPresentOrElse(FoodDto::incrementQuantity,
//                        ()-> addFoodToPersonList(name,personDto));
//        personDto.setCaloriesConsumed(personDto.getTotalCalories().intValue());
//        personDto.setCaloriesLeft(personDto.getGoalCalories()-personDto.getCaloriesLeft());
//    }



    private void setGoals(PersonDto personDto, Person person) {

        person.setGoalCalories(countBmr(personDto).intValue());
        person.setCaloriesConsumed(0);
        person.setCaloriesLeft(countBmr(personDto).intValue());

    }


    private Double countBmr(PersonDto personDto) {
        if (isEquals(personDto.getGender(), WOMAN)) {
            double bmr = 655.1 + (9.563 * personDto.getWeight()) + (1.850 * personDto.getHeight()) - (4.676 * personDto.getAge());
            return countAmr(personDto.getActivityLevel(), bmr);

        } else if (isEquals(personDto.getGender(), MEN)) {
            double bmr = (66.47 + (13.75 * personDto.getWeight()) + (5.003 * personDto.getHeight()) - (6.755 * personDto.getAge()));
            return countAmr(personDto.getActivityLevel(), bmr);

        }
        throw new FoodException("gender not exist: " + personDto.getGender());
    }

    private double countAmr(String activity, Double bmr) {

        if (isEquals(activity, SEDENTARY)) {
            return bmr * 1.2;

        } else if (isEquals(activity, LIGHTLY)) {
            return bmr * 1.375;

        } else if (isEquals(activity, MODERATELY)) {
            return bmr * 1.55;

        } else if (isEquals(activity, ACTIVE)) {
            return bmr * 1.725;

        } else if (isEquals(activity, VERY)) {
            return bmr * 1.9;
        }
        throw new FoodException("Your activity level not exist: " + activity);
    }

    private boolean isEquals(String firsValue, String secondValue) {
        return firsValue.equals(secondValue);
    }
//    private void addFoodToPersonList(String name, PersonDto personDto){
//        Optional<FoodDto> foodDto = foodService.getFoodByName(name);
//        Food food = FoodMapper.FOOD_MAPPER.mapModel(foodDto.get());
//        personDto.add(food);
//
//    }

    private Optional<FoodDto> getFoodList(String foodName, List<FoodDto> foodDtoList) {
        return foodDtoList.stream()
                .filter(food -> food.getName().equals(foodName))
                .findAny();
    }
}
