package com.henrik.calorieapphenrik.food.Repository;

import com.henrik.calorieapphenrik.food.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByPersonName(String name);
}
