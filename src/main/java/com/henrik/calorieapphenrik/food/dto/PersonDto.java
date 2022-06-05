package com.henrik.calorieapphenrik.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private String personName;
    private String gender;
    private Double age;
    private Double weight;
    private Double height;
    private  String activityLevel;
    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;
}
