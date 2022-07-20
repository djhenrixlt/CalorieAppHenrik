package com.henrik.calorieapphenrik.food.mapper;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.Entity.Food.FoodBuilder;
import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.food.Entity.MyList.MyListBuilder;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.FoodDto.FoodDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-16T22:07:35+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class FoodMapperImpl implements FoodMapper {

    @Override
    public FoodDto mapDto(Food food) {
        if ( food == null ) {
            return null;
        }

        FoodDtoBuilder foodDto = FoodDto.builder();

        foodDto.name( food.getName() );
        foodDto.calories( food.getCalories() );
        foodDto.protein( food.getProtein() );
        foodDto.carbs( food.getCarbs() );
        foodDto.fat( food.getFat() );
        foodDto.fiber( food.getFiber() );
        foodDto.userNameCreated( food.getUserNameCreated() );

        return foodDto.build();
    }

    @Override
    public Food mapModel(FoodDto foodDto) {
        if ( foodDto == null ) {
            return null;
        }

        FoodBuilder food = Food.builder();

        food.name( foodDto.getName() );
        food.calories( foodDto.getCalories() );
        food.protein( foodDto.getProtein() );
        food.carbs( foodDto.getCarbs() );
        food.fat( foodDto.getFat() );
        food.fiber( foodDto.getFiber() );
        food.userNameCreated( foodDto.getUserNameCreated() );

        return food.build();
    }

    @Override
    public Food mapForUpdate(FoodDto foodDto, Food food) {
        if ( foodDto == null ) {
            return null;
        }

        food.setName( foodDto.getName() );
        food.setCalories( foodDto.getCalories() );
        food.setProtein( foodDto.getProtein() );
        food.setCarbs( foodDto.getCarbs() );
        food.setFat( foodDto.getFat() );
        food.setFiber( foodDto.getFiber() );
        food.setUserNameCreated( foodDto.getUserNameCreated() );

        return food;
    }

    @Override
    public MyList mapToListModel(FoodDto foodDto) {
        if ( foodDto == null ) {
            return null;
        }

        MyListBuilder myList = MyList.builder();

        myList.name( foodDto.getName() );
        myList.calories( foodDto.getCalories() );
        myList.protein( foodDto.getProtein() );
        myList.carbs( foodDto.getCarbs() );
        myList.fat( foodDto.getFat() );
        myList.fiber( foodDto.getFiber() );

        return myList.build();
    }
}
