package com.henrik.calorieapphenrik.food.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String personName;
    private String gender;
    private Double age;
    private Double weight;
    private Double height;
    private String activityLevel;
    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;

    @OneToMany
    private List<Food> foodList;
}
