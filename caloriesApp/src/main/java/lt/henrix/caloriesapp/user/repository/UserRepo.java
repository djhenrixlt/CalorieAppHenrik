package lt.henrix.caloriesapp.user.repository;


import lt.henrix.caloriesapp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findWithRolesByUsername(@Param("username") String username);

   Optional<User> findByUsername(String username);

   Optional<User> findWithFoodById(Long id);

//   @Query("SELECT u FROM user u join user_food uf where u.id == uf.user_id")
//   List<User> getAllFoodsById(@Param("id") long id);

}
