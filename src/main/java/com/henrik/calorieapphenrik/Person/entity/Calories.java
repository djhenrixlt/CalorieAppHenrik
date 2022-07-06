package com.henrik.calorieapphenrik.Person.entity;

import com.henrik.calorieapphenrik.food.Entity.MyList;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
public class Calories {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;
    private Integer goalProtein;
    private Integer goalCarbs;
    private Integer goalFats;

    @OneToMany(cascade = CascadeType.ALL)
    @NonNull
    private Set<MyList> myFoodList = new HashSet<>();
}

