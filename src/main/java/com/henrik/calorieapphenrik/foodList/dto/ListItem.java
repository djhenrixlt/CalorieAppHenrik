package com.henrik.calorieapphenrik.foodList.dto;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ListItem {

    @Id
    private Long id;


    @ManyToOne
    private Food foodDto;

    private Integer quantity;

    public void incrementQuantity() {
        quantity++;
    }

    public BigDecimal getConsumedCalories(){
        return foodDto.getCalories().multiply(BigDecimal.valueOf(quantity));
    }
}
