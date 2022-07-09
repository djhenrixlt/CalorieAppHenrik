package com.henrik.calorieapphenrik.Person.Repository;

import com.henrik.calorieapphenrik.Person.entity.Calories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaloriesRepo extends JpaRepository<Calories, Long> {
}


