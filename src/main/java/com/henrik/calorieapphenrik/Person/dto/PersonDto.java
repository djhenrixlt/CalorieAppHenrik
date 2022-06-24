package com.henrik.calorieapphenrik.Person.dto;

import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.Person.entity.Person;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.mapper.FoodMapper;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private long id;
    private  String username;
    private  String password;
    private  String fullName;
    private  String email;
    private String gender;
    private Double age;
    private Double weight;
    private Double height;
    private String activityLevel;
    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;
    private Integer goalProtein;
    private Integer goalCarbs;
    private Integer goalFats;
    private String plan;

    @NonNull
    private Set<MyList> myFoodList =  new HashSet<>();

    public Person toUser(PasswordEncoder passwordEncoder){
        return new Person(username, passwordEncoder.encode(password),
                fullName, email, gender,age,weight,height, activityLevel, goalCalories, caloriesLeft, caloriesConsumed, goalProtein, goalCarbs, goalFats, plan);
    }

    public void addToMyLIst(FoodDto foodDto){
        myFoodList.add(FoodMapper.FOOD_MAPPER.mapToListModel(foodDto));
    }

    public void deleteFromMyList(MyList myList){
        myFoodList.remove(myList);
    }

}
