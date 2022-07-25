package lt.henrix.caloriesapp.food.mapper;

import javax.annotation.processing.Generated;
import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.food.Entity.Food.FoodBuilder;
import lt.henrix.caloriesapp.food.dto.FoodDto;
import lt.henrix.caloriesapp.food.dto.FoodDto.FoodDtoBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-25T22:25:19+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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
}
