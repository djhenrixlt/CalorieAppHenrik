package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.service.FoodService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FoodControllerTest {

    private static final String NAME = "Benas";
    private static final String NAME1 = "name";
    private static final Long ID = 1L;
    public static final String NOT_FOUND = "NOT_FOUND";


    @MockBean
    private FoodService foodService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("GET /foods - Found")
    void getAll() throws Exception {
        FoodDto food = getFoodDto();
        //  Setup our mocked service
        doReturn(List.of(food)).when(foodService).getAllFoods();
        // Execute the GET request
        mockMvc.perform(get("/foods"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(NAME1)))
                .andExpect(jsonPath("$[0].calories", is(1)))
                .andExpect(jsonPath("$[0].protein", is(1)))
                .andExpect(jsonPath("$[0].carbs", is(1)))
                .andExpect(jsonPath("$[0].fat", is(1)))
                .andExpect(jsonPath("$[0].fiber", is(1)));

    }

    @Test
    @DisplayName("GET /foods/{foodName} - Found")
    void getByName() throws Exception {
        FoodDto food = getFoodDto();
        //  Setup our mocked service
        doReturn(food).when(foodService).getFoodByName(NAME1);
        // Execute the GET request
        mockMvc.perform(get("/foods/{foodName}", NAME1))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the returned fields
                .andExpect(jsonPath("$.name", is(NAME1)))
                .andExpect(jsonPath("$.calories", is(1)))
                .andExpect(jsonPath("$.protein", is(1)))
                .andExpect(jsonPath("$.carbs", is(1)))
                .andExpect(jsonPath("$.fat", is(1)))
                .andExpect(jsonPath("$.fiber", is(1)));

    }

    @Test
    @DisplayName("GET /foods/{foodName} - NotFound")
    void getByNameNotFound() throws Exception {
        //  Setup our mocked service
        when(foodService.getFoodByName(NOT_FOUND)).thenReturn(Optional.empty());
        // Execute the GET request
        mockMvc.perform(get("/foods/{foodName}", NOT_FOUND))
                // Validate the response code and content type
                .andExpect(status().isNotFound());
    }

    private Food getFood() {
        return Food.builder()
                .calories(1)
                .fat(1)
                .id(1)
                .fiber(1)
                .protein(1)
                .carbs(1)
                .name(NAME)
                .build();
    }

    private FoodDto getFoodDto() {
        return FoodDto.builder()
                .calories(1)
                .fat(1)
                .fiber(1)
                .protein(1)
                .name(NAME1)
                .carbs(1)
                .build();
    }
}
