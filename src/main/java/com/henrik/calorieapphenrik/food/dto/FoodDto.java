package com.henrik.calorieapphenrik.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 1, max = 2000)
    private Integer calories;
    @NotBlank
    private Integer protein;
    @NotBlank
    private Integer carbs;
    @NotBlank
    private Integer fat;
    @NotBlank
    private Integer fiber;


}

