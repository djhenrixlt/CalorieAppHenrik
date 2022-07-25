package lt.henrix.caloriesapp.food.mapper;


import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.food.dto.FoodDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    FoodMapper FOOD_MAPPER = Mappers.getMapper(FoodMapper.class);

    FoodDto mapDto(Food food);

    Food mapModel(FoodDto foodDto);

    Food mapForUpdate(FoodDto foodDto, @MappingTarget Food food);


}
