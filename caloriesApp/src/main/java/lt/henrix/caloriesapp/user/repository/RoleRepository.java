package lt.henrix.caloriesapp.user.repository;


import lt.henrix.caloriesapp.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
