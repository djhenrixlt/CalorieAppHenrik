package com.henrik.calorieapphenrik.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {


    private String name;
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private Integer fiber;
}
