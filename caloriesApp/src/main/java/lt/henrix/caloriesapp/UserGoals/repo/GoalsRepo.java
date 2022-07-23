package lt.henrix.caloriesapp.UserGoals.repo;

import lt.henrix.caloriesapp.UserGoals.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface GoalsRepo extends JpaRepository<Goal, Long> {
}
