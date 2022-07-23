package lt.henrix.caloriesapp.Userdetails.repo;

import lt.henrix.caloriesapp.Userdetails.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
}
