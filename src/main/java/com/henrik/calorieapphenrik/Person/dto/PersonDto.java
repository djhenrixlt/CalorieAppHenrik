package com.henrik.calorieapphenrik.Person.dto;

import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.mapper.FoodMapper;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private long id;
    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;
    private Integer goalProtein;
    private Integer goalCarbs;
    private Integer goalFats;

    @NonNull
    private Set<MyList> myFoodList =  new HashSet<>();


    public void addToMyLIst(FoodDto foodDto){
        myFoodList.add(FoodMapper.FOOD_MAPPER.mapToListModel(foodDto));
    }

    public void deleteFromMyList(MyList myList){
        myFoodList.remove(myList);
    }

}
