package lt.henrix.caloriesapp.user.repository;


import lt.henrix.caloriesapp.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Role, Long> {

}
