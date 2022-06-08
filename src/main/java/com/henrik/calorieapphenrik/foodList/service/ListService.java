package com.henrik.calorieapphenrik.foodList.service;

import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import com.henrik.calorieapphenrik.food.service.FoodService;
import com.henrik.calorieapphenrik.foodList.dto.ListDto;
import com.henrik.calorieapphenrik.foodList.dto.ListItem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ListService {

    private  FoodService foodService;
    private PersonDto personDto;
    private ListItem listItem;

    public void addToLIstByFoodName(String name, ListDto listDto) {
        this.getListItem(name, listDto.getListItems()).ifPresentOrElse(
                ListItem::incrementQuantity,
                () -> addFoodToList(name, listDto));
        personDto.setCaloriesConsumed(listItem.getConsumedCalories().intValue());

    }


    private void addFoodToList(String name, ListDto listDto) {
        Optional<FoodDto> foodDto = foodService.getFoodByName(name);
        listDto.add(foodDto.get());
    }

    private Optional<ListItem> getListItem(String name, List<ListItem> listItems) {
        return listItems.stream()
                .filter(item -> item.getFoodDto().getName().equals(name))
                .findAny();
    }
}
