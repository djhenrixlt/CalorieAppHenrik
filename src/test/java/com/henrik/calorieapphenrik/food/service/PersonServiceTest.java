package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.Entity.Person;
import com.henrik.calorieapphenrik.food.Repository.PersonRepo;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import com.henrik.calorieapphenrik.food.mapper.FoodMapper;
import com.henrik.calorieapphenrik.food.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private static final String WOMAN = "woman";
    private static final String MEN = "men";
    private static final String SEDENTARY = "Sedentary";
    private static final String LIGHTLY = "lightly";
    private static final String MODERATELY = "moderately";
    private static final String ACTIVE = "active";
    private static final String VERY = "very";
    public static final String NAME = "a";
    public static final String NAME_2 = "name2";

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepo personRepo;

    @Test
    void getGoalCalories() {
    }

    @Test
    void savePerson() {

    }




    @Test
    void deletePerson() {
    }
    private Person getPerson() {
        return Person.builder().personName(NAME).activityLevel(MODERATELY).age(20.0)
                .gender(WOMAN).height(170.0).weight(60.0).caloriesConsumed(1)
                .goalCalories(1).caloriesLeft(1).build();
    }
    private PersonDto getPersonDto() {
        return PersonDto.builder().personName(NAME_2).activityLevel(MODERATELY).age(20.0)
                .gender(WOMAN).height(170.0).weight(60.0).caloriesConsumed(1)
                .goalCalories(1).caloriesLeft(1).build();
    }
    private void testPerson(Person person, PersonDto actual) {
        assertEquals(person.getPersonName(), actual.getPersonName());
        assertEquals(person.getActivityLevel(), actual.getActivityLevel());
        assertEquals(person.getAge(), actual.getAge());
        assertEquals(person.getGender(), actual.getGender());
        assertEquals(person.getHeight(), actual.getHeight());
        assertEquals(person.getWeight(), actual.getWeight());
        assertEquals(person.getCaloriesConsumed(), actual.getCaloriesConsumed());
        assertEquals(person.getCaloriesLeft(), actual.getCaloriesLeft());
        assertEquals(person.getGoalCalories(), actual.getGoalCalories());
    }
}
