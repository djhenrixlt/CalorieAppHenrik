package com.henrik.calorieapphenrik.foodList.controller;

import com.henrik.calorieapphenrik.foodList.dto.ListDto;
import com.henrik.calorieapphenrik.foodList.service.ListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/list")
public class ListController {

    private final ListService listService;

    @ModelAttribute("listDto")
    public ListDto createList() {
        return new ListDto();
    }

    @GetMapping("/open")
    public String myFoodList(@ModelAttribute(name = "listDto") ListDto list, Principal principal) {
        return "myFoodList";
    }

    @GetMapping("/all")
    public String getALl(Model mOdel){
        mOdel.addAttribute("listDto", listService.getALL());
        return "myFoodList";

    }

    @PostMapping("/add/{name}")
    public void addToList(@PathVariable String name, @ModelAttribute(name = "listDto")  ListDto listDto) {
        listService.addToLIstByFoodName(name, listDto);
    }

}
