package com.henrik.calorieapphenrik.user.mapper;

import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.user.dto.CaloriesDto;
import com.henrik.calorieapphenrik.user.dto.CaloriesDto.CaloriesDtoBuilder;
import com.henrik.calorieapphenrik.user.entity.Calories;
import com.henrik.calorieapphenrik.user.entity.Calories.CaloriesBuilder;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-16T22:07:35+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CaloriesMapperImpl implements CaloriesMapper {

    @Override
    public CaloriesDto mapDto(Calories calories) {
        if ( calories == null ) {
            return null;
        }

        CaloriesDtoBuilder caloriesDto = CaloriesDto.builder();

        caloriesDto.id( calories.getId() );
        caloriesDto.goalCalories( calories.getGoalCalories() );
        caloriesDto.caloriesLeft( calories.getCaloriesLeft() );
        caloriesDto.caloriesConsumed( calories.getCaloriesConsumed() );
        caloriesDto.goalProtein( calories.getGoalProtein() );
        caloriesDto.goalCarbs( calories.getGoalCarbs() );
        caloriesDto.goalFats( calories.getGoalFats() );
        Set<MyList> set = calories.getMyFoodList();
        if ( set != null ) {
            caloriesDto.myFoodList( new HashSet<MyList>( set ) );
        }

        return caloriesDto.build();
    }

    @Override
    public Calories mapModel(CaloriesDto caloriesDto) {
        if ( caloriesDto == null ) {
            return null;
        }

        CaloriesBuilder calories = Calories.builder();

        calories.id( caloriesDto.getId() );
        calories.goalCalories( caloriesDto.getGoalCalories() );
        calories.caloriesLeft( caloriesDto.getCaloriesLeft() );
        calories.caloriesConsumed( caloriesDto.getCaloriesConsumed() );
        calories.goalProtein( caloriesDto.getGoalProtein() );
        calories.goalCarbs( caloriesDto.getGoalCarbs() );
        calories.goalFats( caloriesDto.getGoalFats() );
        Set<MyList> set = caloriesDto.getMyFoodList();
        if ( set != null ) {
            calories.myFoodList( new HashSet<MyList>( set ) );
        }

        return calories.build();
    }

    @Override
    public CaloriesDto mapDtoForSave(Calories calories) {
        if ( calories == null ) {
            return null;
        }

        CaloriesDtoBuilder caloriesDto = CaloriesDto.builder();

        caloriesDto.id( calories.getId() );
        caloriesDto.goalCalories( calories.getGoalCalories() );
        caloriesDto.caloriesLeft( calories.getCaloriesLeft() );
        caloriesDto.caloriesConsumed( calories.getCaloriesConsumed() );
        caloriesDto.goalProtein( calories.getGoalProtein() );
        caloriesDto.goalCarbs( calories.getGoalCarbs() );
        caloriesDto.goalFats( calories.getGoalFats() );

        return caloriesDto.build();
    }

    @Override
    public Calories mapModelSave(CaloriesDto caloriesDto) {
        if ( caloriesDto == null ) {
            return null;
        }

        CaloriesBuilder calories = Calories.builder();

        calories.id( caloriesDto.getId() );
        calories.goalCalories( caloriesDto.getGoalCalories() );
        calories.caloriesLeft( caloriesDto.getCaloriesLeft() );
        calories.caloriesConsumed( caloriesDto.getCaloriesConsumed() );
        calories.goalProtein( caloriesDto.getGoalProtein() );
        calories.goalCarbs( caloriesDto.getGoalCarbs() );
        calories.goalFats( caloriesDto.getGoalFats() );

        return calories.build();
    }

    @Override
    public Calories mapForUpdate(CaloriesDto caloriesDto, Calories calories) {
        if ( caloriesDto == null ) {
            return null;
        }

        calories.setId( caloriesDto.getId() );
        calories.setGoalCalories( caloriesDto.getGoalCalories() );
        calories.setCaloriesLeft( caloriesDto.getCaloriesLeft() );
        calories.setCaloriesConsumed( caloriesDto.getCaloriesConsumed() );
        calories.setGoalProtein( caloriesDto.getGoalProtein() );
        calories.setGoalCarbs( caloriesDto.getGoalCarbs() );
        calories.setGoalFats( caloriesDto.getGoalFats() );
        if ( calories.getMyFoodList() != null ) {
            Set<MyList> set = caloriesDto.getMyFoodList();
            if ( set != null ) {
                calories.getMyFoodList().clear();
                calories.getMyFoodList().addAll( set );
            }
            else {
                calories.setMyFoodList( null );
            }
        }
        else {
            Set<MyList> set = caloriesDto.getMyFoodList();
            if ( set != null ) {
                calories.setMyFoodList( new HashSet<MyList>( set ) );
            }
        }

        return calories;
    }
}
