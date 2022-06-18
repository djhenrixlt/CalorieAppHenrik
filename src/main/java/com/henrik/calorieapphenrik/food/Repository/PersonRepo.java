package com.henrik.calorieapphenrik.food.Repository;

import com.henrik.calorieapphenrik.food.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByEmail(String name);
    Optional<Person> findByUsername(String userNAme);
}


