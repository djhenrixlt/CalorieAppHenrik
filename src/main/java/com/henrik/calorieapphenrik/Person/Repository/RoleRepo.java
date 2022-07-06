package com.henrik.calorieapphenrik.Person.Repository;

import com.henrik.calorieapphenrik.Person.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
