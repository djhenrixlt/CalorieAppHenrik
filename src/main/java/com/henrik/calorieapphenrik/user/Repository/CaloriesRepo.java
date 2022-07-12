package com.henrik.calorieapphenrik.user.Repository;

import com.henrik.calorieapphenrik.user.entity.Calories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaloriesRepo extends JpaRepository<Calories, Long> {
}


