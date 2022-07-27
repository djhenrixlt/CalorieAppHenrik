package lt.henrix.caloriesapp.food.service;

import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.food.Repository.FoodRepo;
import lt.henrix.caloriesapp.food.dto.FoodDto;
import lt.henrix.caloriesapp.food.mapper.FoodMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {
    private static final String NAME = "Benas";
    private static final String NAME1 = "name";
    private static final Long ID = 1L;
    public static final int CALORIES = 300;
    @InjectMocks
    private FoodService foodService;

    @Mock
    private FoodRepo foodRepo;

    @Captor
    ArgumentCaptor<Food> foodCaptor;

    @Test
    void getAllFoods() {
        Food food = Food.builder()
                .calories(100)
                .fat(20)
                .id(1L)
                .fiber(4)
                .protein(30)
                .name("Benas")
                .build();
        Food food2 = Food.builder()
                .calories(10)
                .fat(20)
                .fiber(4)
                .protein(30)
                .name("l")
                .build();
        List<Food> foodList = List.of(food, food2);
        List<FoodDto> mappedList = foodList.stream().map(FoodMapper.FOOD_MAPPER::mapDto).toList();

        when(foodRepo.findAll()).thenReturn(foodList);
        List<FoodDto> actual = foodService.getAllFoods();

        verify(foodRepo).findAll();
        assertEquals(foodList.size(), actual.size());
        assertIterableEquals(mappedList, actual);
    }

    @Test
    void getFoodByName() {
        Food food = getFood();

        when(foodRepo.findByName(NAME)).thenReturn(Optional.of(food));

        Optional<FoodDto> actual = foodService.getFoodByName(NAME);

        testFood(food, actual.get());
    }

    @Test
    void updateFood() {
        Optional<Food> food  = Optional.ofNullable(getFood());
        FoodDto foodDto = FoodMapper.FOOD_MAPPER.mapDto(food.get());


        when(foodRepo.existsById(ID)).thenReturn(true);
        when(foodRepo.findById(ID)).thenReturn(food);

        Food update = FoodMapper.FOOD_MAPPER.mapForUpdate(foodDto, food.get());
        FoodDto actual = foodService.updateFood(foodDto, ID);

        verify(foodRepo).existsById(ID);
        verify(foodRepo).save(update);

        testFood(update, actual);
    }

    @Test
    void saveFood() {
        FoodDto foodDto = getFoodDto();
        Food food = FoodMapper.FOOD_MAPPER.mapModel(foodDto);

        when(foodRepo.save(food)).thenReturn(food);

        FoodDto actual = foodService.saveFood(foodDto, "user1");

        verify(foodRepo).save(food);
        testFood(food, actual);
    }

    @Test
    void deleteFood() throws Exception {
        Optional<Food> food = Optional.ofNullable(getFood());

        when(foodRepo.findByName(NAME)).thenReturn(food);
        when(foodRepo.existsById(ID)).thenReturn(true);

        foodService.deleteFood(NAME, "admin");
        verify(foodRepo).delete(foodCaptor.capture());
        Food capturedFood = foodCaptor.getValue();
        testFood(food.get(), FoodMapper.FOOD_MAPPER.mapDto(capturedFood));
    }

    @Test
    void getCalories(){
        Integer calories =  300;

        when(foodRepo.getCaloriesSUm()).thenReturn(CALORIES);

        Integer actual = foodService.getCalories();

        assertEquals(calories, actual);
    }

    private Food getFood() {
        return Food.builder()
                .calories(100)
                .fat(20)
                .id(1L)
                .fiber(4)
                .protein(30)
                .name(NAME)
                .build();
    }

    private FoodDto getFoodDto() {
        return FoodDto.builder()
                .calories(10)
                .fat(20)
                .fiber(4)
                .protein(30)
                .name(NAME1)
                .build();
    }

    private void testFood(Food food, FoodDto actual) {
        assertEquals(food.getName(), actual.getName());
        assertEquals(food.getCalories(), actual.getCalories());
        assertEquals(food.getCarbs(), actual.getCarbs());
        assertEquals(food.getFat(), actual.getFat());
        assertEquals(food.getProtein(), actual.getProtein());
        assertEquals(food.getFiber(), actual.getFiber());
    }

}
