package com.henrik.calorieapphenrik.food.mapper;

import com.henrik.calorieapphenrik.food.Entity.MyList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyListRepo  extends JpaRepository<MyList, Long> {
}
