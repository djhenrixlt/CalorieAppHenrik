package com.henrik.calorieapphenrik.food.dto;

import com.henrik.calorieapphenrik.food.Entity.MyList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String gender;
    private Double age;
    private String email;
    private Double weight;
    private Double height;
    private String activityLevel;
    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;

    private Set<MyList> myFoodList ;



}
