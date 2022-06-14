package com.henrik.calorieapphenrik.foodList.service;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import com.henrik.calorieapphenrik.food.mapper.FoodMapper;
import com.henrik.calorieapphenrik.food.service.FoodService;
import com.henrik.calorieapphenrik.foodList.dto.ListDto;
import com.henrik.calorieapphenrik.foodList.dto.ListItem;
import com.henrik.calorieapphenrik.foodList.repository.ListRepo;
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
    private ListRepo listRepo;

    public void addToLIstByFoodName(String name, ListDto listDto) {
        getListItem(name, listDto.getListItems()).ifPresentOrElse(
                ListItem::incrementQuantity,
                () -> addFoodToList(name, listDto));
        personDto.setCaloriesConsumed(listItem.getConsumedCalories().intValue());

    }
    public List<ListDto> getALL(){
        return listRepo.findAll();
    }


    private void addFoodToList(String name, ListDto listDto) {
        Optional<FoodDto> foodDto = foodService.getFoodByName(name);
        Optional<Food> food = Optional.ofNullable(FoodMapper.FOOD_MAPPER.mapModel(foodDto.get()));
        listDto.addFood(food.get());
        listRepo.save(listDto);

    }

    private Optional<ListItem> getListItem(String name, List<ListItem> listItems) {
        return listItems.stream()
                .filter(item -> item.getFoodDto().getName().equals(name))
                .findAny();
    }

}
