package com.henrik.calorieapphenrik.user.Repository;

import com.henrik.calorieapphenrik.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}
