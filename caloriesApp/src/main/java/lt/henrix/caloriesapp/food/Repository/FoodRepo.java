package lt.henrix.caloriesapp.food.Repository;


import lt.henrix.caloriesapp.food.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {


    List<Food> findAll();

    Optional<Food> findByName(String name);

    @Query(value = "SELECT SUM(calories) FROM food ", nativeQuery = true)
    Integer getCaloriesSUm();

    @Query("SELECT p FROM  Food  p WHERE CONCAT(p.name, ' ') LIKE %?1%")
//    +"OR p.calories LIKE  %?1%")
    List<Food> search(String keyWord);
}
