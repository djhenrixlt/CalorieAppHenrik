package com.henrik.calorieapphenrik.foodList.controller;

import com.henrik.calorieapphenrik.foodList.dto.ListDto;
import com.henrik.calorieapphenrik.foodList.service.ListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/list")
public class ListController {

    private final ListService listService;

    @PostMapping("/add/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addToList(@PathVariable String name, @RequestBody @Valid ListDto listDto) {
        listService.addToLIstByFoodName(name, listDto);
    }
}
