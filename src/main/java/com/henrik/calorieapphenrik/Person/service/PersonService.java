package com.henrik.calorieapphenrik.Person.service;


import com.henrik.calorieapphenrik.Person.Repository.PersonRepo;
import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import com.henrik.calorieapphenrik.Person.entity.Person;
import com.henrik.calorieapphenrik.Person.mapper.PersonMapper;
import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.food.Exception.FoodException;
import com.henrik.calorieapphenrik.food.Exception.PersonException;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.mapper.MyListRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private final PersonRepo personRepo;
    private final MyListRepo myListRepo;
    private final PasswordEncoder passwordEncoder;

    //Gets calories just only by value do not save data
    public Integer getGoalCalories(PersonDto personDto) {
        return countBmr(personDto).intValue();
    }

    public PersonDto savePerson(PersonDto personDto) {
        Person person = PersonMapper.PERSON_MAPPER.mapModel(personDto);
        setGoals(personDto);
        personRepo.save(personDto.toUser(passwordEncoder));

        return PersonMapper.PERSON_MAPPER.mapDto(person);
    }

    public List<Integer> getAllCaLByName(String name) {
        Person person = personRepo.findByEmail(name);
        if (isEquals(person.getUsername(), name)) {
            throw new PersonException("person not Exist" + name);
        }
        return List.of(person.getGoalCalories(), person.getCaloriesConsumed(), person.getCaloriesLeft());
    }

    public PersonDto getByUserName(String name) {
        Optional<Person> person = personRepo.findByUsername(name);
        if (!isEquals(person.get().getUsername(), name)) {
            throw new PersonException("user not exist");
        }
        return PersonMapper.PERSON_MAPPER.mapDto(person.get());
    }

    public void deletePerson(String name) {
        Person person = personRepo.findByEmail(name);
        Long id = person.getId();
        if (!personRepo.existsById(id)) {
            throw new PersonException("Name not exist");
        }
        personRepo.delete(person);
    }


    public List<MyList> AllMyList() {
        return myListRepo.findAll();
    }


    public PersonDto findByID(Long id) {
        Optional<Person> person = personRepo.findById(id);
        return PersonMapper.PERSON_MAPPER.mapDto(person.get());
    }


    private void setGoals(PersonDto personDto) {
//        person.setGoalCalories(countBmr(personDto).intValue());
//        person.setCaloriesConsumed(0);
//        person.setCaloriesLeft(countBmr(personDto).intValue());

        personDto.setGoalCalories(countYourPlan(personDto.getPlan(), countBmr(personDto).intValue()).intValue());
        personDto.setCaloriesConsumed(0);
        personDto.setCaloriesLeft(countYourPlan(personDto.getPlan(), countBmr(personDto).intValue()).intValue());
        personDto.setGoalProtein(countProteinGoal(personDto.getActivityLevel(), personDto.getWeight()).intValue());
        personDto.setGoalCarbs(getCarbsGoal(personDto.getGoalCalories()));
        personDto.setGoalFats(getFatGoal(personDto.getGoalCalories()).intValue());
    }

    private Double getFatGoal(Integer goalCal) {
        return (goalCal * 0.3) / 9;
    }

    private int getCarbsGoal(Integer calories) {
        return (calories / 2) / 4;
    }

    private Double countBmr(PersonDto personDto) {
        return switch (personDto.getGender()) {
            case WOMAN -> countAmr(personDto.getActivityLevel(), getBmrWoman(personDto));
            case MEN -> countAmr(personDto.getActivityLevel(), getBmrMen(personDto));
            default -> throw new FoodException("gender not exist: " + personDto.getGender());
        };
    }

    private double countAmr(String activity, Double bmr) {
        return switch (activity) {
            case SEDENTARY -> bmr * 1.2;
            case LIGHTLY -> bmr * 1.375;
            case MODERATELY -> bmr * 1.55;
            case ACTIVE -> bmr * 1.725;
            case VERY -> bmr * 1.9;
            default -> throw new FoodException("Your activity level not exist: " + activity);
        };
    }

    private Double countProteinGoal(String activity, Double weight) {
        return switch (activity) {
            case SEDENTARY -> weight * 0.7;
            case LIGHTLY -> weight * 0.9;
            case MODERATELY -> weight * 1.5;
            case ACTIVE -> weight * 1.8;
            case VERY -> weight * 2;
            default -> throw new FoodException("Your activity level not exist: " + activity);
        };
    }

    private Double countYourPlan(String level, Integer goalCalories) {
        return switch (level) {
            case "lost10" -> goalCalories * 0.9;
            case "lost20" -> goalCalories * 0.8;
            case "gain1" -> goalCalories + 200.0;
            case "gain2" -> goalCalories + 500.0;
            case "same" -> goalCalories.doubleValue();
            default -> throw new FoodException("Your activity level not exist: " + level);
        };
    }

    private double getBmrWoman(PersonDto personDto) {
        return 655.1 + (9.563 * personDto.getWeight()) + (1.850 * personDto.getHeight()) - (4.676 * personDto.getAge());
    }

    private double getBmrMen(PersonDto personDto) {
        return 66.47 + (13.75 * personDto.getWeight()) + (5.003 * personDto.getHeight()) - (6.755 * personDto.getAge());
    }

    private boolean isEquals(String firsValue, String secondValue) {
        return firsValue.equals(secondValue);
    }


    private Optional<FoodDto> getFoodList(String foodName, List<FoodDto> foodDtoList) {
        return foodDtoList.stream()
                .filter(food -> food.getName().equals(foodName))
                .findAny();
    }
}
