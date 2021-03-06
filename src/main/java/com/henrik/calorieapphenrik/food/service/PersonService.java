package com.henrik.calorieapphenrik.food.service;


import com.henrik.calorieapphenrik.food.Entity.Person;
import com.henrik.calorieapphenrik.food.Exception.FoodException;
import com.henrik.calorieapphenrik.food.Exception.PersonException;
import com.henrik.calorieapphenrik.food.Repository.FoodRepo;
import com.henrik.calorieapphenrik.food.Repository.PersonRepo;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
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

    private static final String WOMAN = "woman";
    private static final String MEN = "men";
    private static final String SEDENTARY = "sedentary";
    private static final String LIGHTLY = "lightly";
    private static final String MODERATELY = "moderately";
    private static final String ACTIVE = "active";
    private static final String VERY = "very";

    //Gets calories just only by value do not saves data
    public Integer getGoalCalories(PersonDto personDto) {
        return countBmr(personDto).intValue();
    }

    public PersonDto savePerson(PersonDto personDto) {
        Person person = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        setGoals(personDto, person);
        personRepo.save(person);
        return PersonMapper.PERSON_MAPPER.mapDto(person);
    }

    public List<Integer> getAllCaLByName(String name) {
        Person person = personRepo.findByPersonName(name);
        if (isEquals(person.getPersonName(), name)) {
            throw new PersonException("person not Exist" + name);
        }
        return List.of(person.getGoalCalories(), person.getCaloriesConsumed(), person.getCaloriesLeft());
    }

    public void deletePerson(String name) {
        Person person = personRepo.findByPersonName(name);
        Long id = person.getId();
        if (!personRepo.existsById(id)) {
            throw new PersonException("Name not exist");
        }
        personRepo.delete(person);
    }

    private void setGoals(PersonDto personDto, Person person) {
        person.setGoalCalories(countBmr(personDto).intValue());
        person.setCaloriesConsumed(foodRepo.getCaloriesSUm());
        person.setCaloriesLeft(countBmr(personDto).intValue() - foodRepo.getCaloriesSUm());
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
