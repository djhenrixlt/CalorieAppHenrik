package com.henrik.calorieapphenrik.myList.repository;

import com.henrik.calorieapphenrik.myList.Entity.MyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyListRepo extends JpaRepository<MyList, Long> {
}
