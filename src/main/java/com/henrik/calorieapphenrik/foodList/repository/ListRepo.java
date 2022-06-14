package com.henrik.calorieapphenrik.foodList.repository;

import com.henrik.calorieapphenrik.foodList.dto.ListDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepo extends JpaRepository<ListDto, Long> {

}
