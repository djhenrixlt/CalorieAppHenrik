package com.henrik.calorieapphenrik.foodList.dto;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ListItem {

    private final FoodDto foodDto;

    private Integer quantity;

    public void incrementQuantity() {
        quantity++;
    }

    public BigDecimal getConsumedCalories(){
        return foodDto.getCalories().multiply(BigDecimal.valueOf(quantity));
    }
}
