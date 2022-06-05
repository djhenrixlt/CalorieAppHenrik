package com.henrik.calorieapphenrik.food.mapper;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.Entity.Goals;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.GoalsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    GoalMapper GOAL_MAPPER = Mappers.getMapper(GoalMapper.class);

    GoalsDto mapDto(Goals goals);

    Goals mapModel(GoalsDto goalsDto);
}
