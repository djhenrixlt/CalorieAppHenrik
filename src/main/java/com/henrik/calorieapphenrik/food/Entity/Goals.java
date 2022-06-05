package com.henrik.calorieapphenrik.food.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goals {

    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;
}
