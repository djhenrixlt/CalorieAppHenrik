package com.henrik.calorieapphenrik.Person.service;


import com.henrik.calorieapphenrik.Person.Repository.CaloriesRepo;
import com.henrik.calorieapphenrik.Person.Repository.UserRepo;
import com.henrik.calorieapphenrik.Person.dto.CaloriesDto;
import com.henrik.calorieapphenrik.Person.dto.UserDto;
import com.henrik.calorieapphenrik.Person.entity.Calories;
import com.henrik.calorieapphenrik.Person.entity.User;
import com.henrik.calorieapphenrik.Person.mapper.CaloriesMapper;
import com.henrik.calorieapphenrik.Person.mapper.UserMapper;
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
    private final CaloriesRepo caloriesRepo;
    private final MyListRepo myListRepo;
    private final PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    //Gets calories just only by value do not save data
//    public Integer getGoalCalories(CaloriesDto personDto) {
//        return countBmr(personDto).intValue();
//    }

    public UserDto savePerson(UserDto userDto) {
        CaloriesDto caloriesDto = new CaloriesDto();
        Optional<User> usernameIs = userRepo.findByUsername(userDto.getUsername());
        if (!usernameIs.isEmpty()){
            throw  new PersonException("User name is taken");
        }
        setGoals(caloriesDto,userDto);
        User user = UserMapper.USER_MAPPER.mapModel(userDto);

        caloriesRepo.save(CaloriesMapper.CALORIES_MAPPER.mapModel(caloriesDto));
        userRepo.save(userDto.toUser(passwordEncoder));

        return UserMapper.USER_MAPPER.mapDto(user);
    }

//    public List<Integer> getAllCaLByName(String name) {
//        Calories person = personRepo.findByEmail(name);
//        if (isEquals(person.getUsername(), name)) {
//            throw new PersonException("person not Exist" + name);
//        }
//        return List.of(person.getGoalCalories(), person.getCaloriesConsumed(), person.getCaloriesLeft());
//    }

    public UserDto getByUserName(String name) {
        Optional<User> user = userRepo.findByUsername(name);
        if (!isEquals(user.get().getUsername(), name)) {
            throw new PersonException("user not exist");
        }
        return UserMapper.USER_MAPPER.mapDto(user.get());
    }

    public void deletePerson(String name) {
        User user = userRepo.findByEmail(name);
        Long id = user.getId();
        if (!userRepo.existsById(id)) {
            throw new PersonException("Name not exist");
        }
        userRepo.delete(user);
    }


    public List<MyList> AllMyList() {
        return myListRepo.findAll();
    }


    public CaloriesDto findByID(Long id) {
        Optional<Calories> person = caloriesRepo.findById(id);
        return CaloriesMapper.CALORIES_MAPPER.mapDto(person.get());
    }


    private void setGoals(CaloriesDto caloriesDto, UserDto userDto) {
//        person.setGoalCalories(countBmr(caloriesDto).intValue());
//        person.setCaloriesConsumed(0);
//        person.setCaloriesLeft(countBmr(caloriesDto).intValue());

        caloriesDto.setGoalCalories(countYourPlan(userDto.getPlan(), countBmr(userDto).intValue()).intValue());
        caloriesDto.setCaloriesConsumed(0);
        caloriesDto.setCaloriesLeft(countYourPlan(userDto.getPlan(), countBmr(userDto).intValue()).intValue());
        caloriesDto.setGoalProtein(countProteinGoal(userDto.getActivityLevel(), userDto.getWeight()).intValue());
        caloriesDto.setGoalCarbs(getCarbsGoal(caloriesDto.getGoalCalories()));
        caloriesDto.setGoalFats(getFatGoal(caloriesDto.getGoalCalories()).intValue());
    }

    private Double getFatGoal(Integer goalCal) {
        return (goalCal * 0.3) / 9;
    }

    private int getCarbsGoal(Integer calories) {
        return (calories / 2) / 4;
    }

    private Double countBmr(UserDto userDto) {
        return switch (userDto.getGender()) {
            case WOMAN -> countAmr(userDto.getActivityLevel(), getBmrWoman(userDto));
            case MEN -> countAmr(userDto.getActivityLevel(), getBmrMen(userDto));
            default -> throw new FoodException("gender not exist: " + userDto.getGender());
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

    private double getBmrWoman(UserDto userDto) {
        return 655.1 + (9.563 * userDto.getWeight()) + (1.850 * userDto.getHeight()) - (4.676 * userDto.getAge());
    }

    private double getBmrMen(UserDto userDto) {
        return 66.47 + (13.75 * userDto.getWeight()) + (5.003 * userDto.getHeight()) - (6.755 * userDto.getAge());
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
