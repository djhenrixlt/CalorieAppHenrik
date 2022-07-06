package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.Person.entity.Calories;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import com.henrik.calorieapphenrik.Person.Repository.PersonRepo;
import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import com.henrik.calorieapphenrik.Person.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CaloriesServiceTest {

    private static final String WOMAN = "woman";
    private static final String MEN = "men";
    private static final String SEDENTARY = "Sedentary";
    private static final String LIGHTLY = "lightly";
    private static final String MODERATELY = "moderately";
    private static final String ACTIVE = "active";
    private static final String VERY = "very";
    private static final String NAME = "a";
    private static final String NAME_2 = "name2";


    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepo personRepo;

    @Captor
    ArgumentCaptor<Calories> personCaptor;

    @Test
    void getGoalCalories() {
    }

    @Test
    void savePerson() {

    }




    @Test
    void deletePerson() {
        Calories calories = getPerson();

        when(personRepo.findByEmail(NAME)).thenReturn(calories);
        when(personRepo.existsById(calories.getId())).thenReturn(true);

        personService.deletePerson(NAME);
        verify(personRepo).delete(personCaptor.capture());
        Calories capturedCalories = personCaptor.getValue();
        testPerson(calories, PersonMapper.PERSON_MAPPER.mapDtoForSavePerson(capturedCalories));
    }
    private Calories getPerson() {
        return Calories.builder().firstName(NAME).activityLevel(MODERATELY).age(20.0)
                .gender(WOMAN).height(170.0).weight(60.0).caloriesConsumed(1)
                .goalCalories(1).caloriesLeft(1).build();
    }
    private PersonDto getPersonDto() {
        return PersonDto.builder().firstName(NAME_2).activityLevel(MODERATELY).age(20.0)
                .gender(WOMAN).height(170.0).weight(60.0).caloriesConsumed(1)
                .goalCalories(1).caloriesLeft(1).build();
    }
    private void testPerson(Calories calories, PersonDto actual) {
        assertEquals(calories.getUsername(), actual.getFirstName());
        assertEquals(calories.getActivityLevel(), actual.getActivityLevel());
        assertEquals(calories.getAge(), actual.getAge());
        assertEquals(calories.getGender(), actual.getGender());
        assertEquals(calories.getHeight(), actual.getHeight());
        assertEquals(calories.getWeight(), actual.getWeight());
        assertEquals(calories.getCaloriesConsumed(), actual.getCaloriesConsumed());
        assertEquals(calories.getCaloriesLeft(), actual.getCaloriesLeft());
        assertEquals(calories.getGoalCalories(), actual.getGoalCalories());
    }
}
