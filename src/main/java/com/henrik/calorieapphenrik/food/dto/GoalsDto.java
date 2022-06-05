package com.henrik.calorieapphenrik.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoalsDto {
    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;
}
