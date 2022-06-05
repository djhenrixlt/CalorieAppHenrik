package com.henrik.calorieapphenrik.food.service;


import com.henrik.calorieapphenrik.food.Entity.Goals;
import com.henrik.calorieapphenrik.food.Entity.Person;
import com.henrik.calorieapphenrik.food.Exception.FoodException;
import com.henrik.calorieapphenrik.food.Repository.FoodRepo;
import com.henrik.calorieapphenrik.food.Repository.PersonRepo;
import com.henrik.calorieapphenrik.food.dto.GoalsDto;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import com.henrik.calorieapphenrik.food.mapper.GoalMapper;
import com.henrik.calorieapphenrik.food.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {

    private FoodRepo foodRepo;
    private PersonRepo personRepo;

    public static final String WOMAN = "woman";
    public static final String MEN = "men";
    public static final String SEDENTARY = "Sedentary";
    public static final String LIGHTLY = "lightly";
    public static final String MODERATELY = "moderately";
    public static final String ACTIVE = "active";
    public static final String VERY = "very";

    public Integer getGoalCalories(PersonDto personDto) {

        return countBmr(personDto).intValue();
    }
    public PersonDto savePerson(PersonDto personDto){
        PersonDto goals = getGoals(personDto);
        Person person = personRepo.save(PersonMapper.PERSON_MAPPER.mapModel(personDto));
        return PersonMapper.PERSON_MAPPER.mapDto(person);
    }
    public List<Integer> getAllCaLByName(String name){
        Person person = personRepo.findByPersonName(name);
        PersonDto personDto = PersonMapper.PERSON_MAPPER.mapDto(person);
        return  List.of(personDto.getGoalCalories(),personDto.getCaloriesConsumed(), personDto.getCaloriesLeft());
    }


    private PersonDto getGoals(PersonDto personDto) {
        return PersonDto.builder().goalCalories(countBmr(personDto).intValue()).caloriesConsumed(foodRepo.getCaloriesSUm()).caloriesLeft(countBmr(personDto).intValue() - foodRepo.getCaloriesSUm()).build();
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
        return firsValue.toLowerCase().equals(secondValue);
    }
}
