package lt.henrix.caloriesapp.food.Repository;


import lt.henrix.caloriesapp.food.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface FoodRepo extends JpaRepository<Food, Long> {


    List<Food> findAll();

    Optional<Food> findByName(String name);

    @Query(value = "SELECT SUM(calories) FROM food ", nativeQuery = true)
    Integer getCaloriesSUm();

    @Query("SELECT p FROM  Food  p WHERE CONCAT(p.name, ' ') LIKE %?1%")
//    +"OR p.calories LIKE  %?1%")
    List<Food> search(String keyWord);

    @Query("FROM Food AS rdt LEFT JOIN  rdt.userFoods AS u WHERE u.id = :id")
    List<Food> findAllWithUserFoodsByUserId(Long id);


    @Query(value = "INSERT INTO USER_FOODS (USER_id, food_id) VALUES(:user_id, :food_id)", nativeQuery = true)
    void saveUserFood(@Param("user_id") Long userId, @Param("food_id") Long foodId);
}
