package com.henrik.calorieapphenrik.foodList.dto;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter

public class ListDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private List<ListItem> listItems = new ArrayList<>();

    public void add(FoodDto foodDto) {
        listItems.stream()
                .map(ListItem::getConsumedCalories)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public Integer getListTotalQuantity() {
        return listItems.stream()
                .map(ListItem::getQuantity)
                .reduce(0, Integer::sum);
    }

}
