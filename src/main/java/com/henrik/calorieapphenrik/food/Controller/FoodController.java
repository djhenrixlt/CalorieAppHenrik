package com.henrik.calorieapphenrik.food.Controller;

import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import com.henrik.calorieapphenrik.Person.service.PersonService;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.PostgreSQL94Dialect;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;
    private final PersonService personService;



    @GetMapping("/main")
    public String mainFrom(){
        return "main";
    }


    @GetMapping("/add")
    public String foodForm(@ModelAttribute(name = "foodDto") FoodDto foodDto){
        return "foodForm";
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("foods",foodService.getAllFoods());
        return "foodList";
    }
    @GetMapping("/filter")
    public String getAllFilter(Model model, @Param("keyword") String keyword, Principal principal) {

        List<FoodDto> foodDtoList = foodService.filter(keyword);

        model.addAttribute("foods",foodDtoList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("foods",foodDtoList);
        return "foodList";
    }


    @GetMapping("{name}")
    public String getByName(Model model, @PathVariable("name") String name) {
        model.addAttribute("foodDto", foodService.getFoodByName(name));
        return "foodForm";

    }

    @GetMapping("/calories")
    public ResponseEntity<Integer> getFoodCalories() {
        return ResponseEntity.ok(foodService.getCalories());
    }


    @PostMapping
    public String create(@ModelAttribute(name = "foodDto") @Valid FoodDto food, Principal principal) {
        foodService.saveFood(food, principal.getName());
        return "redirect:/persons/main/?hex=foodList";
    }

    @PutMapping("/update/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')"
            + "or authentication.principal.equals(#post.member) ")
    public String update(@ModelAttribute(name = "foodDto") @Valid FoodDto foodDto, @PathVariable("id") Long id) {
       foodService.updateFood(foodDto, id);
       return "redirect:/persons/main/?hex=foodList";
    }

    @PostMapping("/delete")
    @PreAuthorize(value = "hasAuthority('ADMIN')"
            + "or authentication.principal.equals(#principal.name) ")
    public String delete(@RequestParam String name, Principal principal ) {
        foodService.deleteFood(name, principal.getName());
        return "redirect:/persons/main/?hex=foodList";
    }


}
