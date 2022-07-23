package lt.henrix.caloriesapp.food.mapper;


import lt.henrix.caloriesapp.food.Entity.MyList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyListRepo  extends JpaRepository<MyList, Long> {
}
