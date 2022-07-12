package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.user.Repository.CaloriesRepo;
import com.henrik.calorieapphenrik.user.dto.CaloriesDto;
import com.henrik.calorieapphenrik.user.entity.Calories;
import com.henrik.calorieapphenrik.user.service.PersonService;
import com.henrik.calorieapphenrik.user.mapper.CaloriesMapper;
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
    private CaloriesRepo caloriesRepo;

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

        when(caloriesRepo.findByEmail(NAME)).thenReturn(calories);
        when(caloriesRepo.existsById(calories.getId())).thenReturn(true);

        personService.deletePerson(NAME);
        verify(caloriesRepo).delete(personCaptor.capture());
        Calories capturedCalories = personCaptor.getValue();
        testPerson(calories, CaloriesMapper.CALORIES_MAPPER.mapDtoForSave(capturedCalories));
    }
    private Calories getPerson() {
        return Calories.builder().firstName(NAME).activityLevel(MODERATELY).age(20.0)
                .gender(WOMAN).height(170.0).weight(60.0).caloriesConsumed(1)
                .goalCalories(1).caloriesLeft(1).build();
    }
    private CaloriesDto getPersonDto() {
        return CaloriesDto.builder().firstName(NAME_2).activityLevel(MODERATELY).age(20.0)
                .gender(WOMAN).height(170.0).weight(60.0).caloriesConsumed(1)
                .goalCalories(1).caloriesLeft(1).build();
    }
    private void testPerson(Calories calories, CaloriesDto actual) {
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
