package com.henrik.calorieapphenrik.foodList.dto;

import com.henrik.calorieapphenrik.food.Entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ListDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<ListItem> listItems = new ArrayList<>();

    public void addFood(Food foodDto){
      listItems.add(ListItem.builder()
                .foodDto(foodDto)
                .quantity(1)
                .build());
    }

    public BigDecimal getListTotalCalories() {
        return listItems.stream()
                .map(ListItem::getConsumedCalories)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public Integer getListTotalQuantity() {
        return listItems.stream()
                .map(ListItem::getQuantity)
                .reduce(0, Integer::sum);
    }

}
