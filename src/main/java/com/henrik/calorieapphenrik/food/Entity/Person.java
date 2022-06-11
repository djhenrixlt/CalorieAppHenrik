package com.henrik.calorieapphenrik.food.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String password;
    private String gender;
    private Double age;
    private String email;
    private Double weight;
    private Double height;
    private String activityLevel;
    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;
}
