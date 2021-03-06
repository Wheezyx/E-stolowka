package pl.prodzajto.estolowkabackend.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.prodzajto.estolowkabackend.user.UserEntity;

import java.util.Optional;
import java.util.Set;

public interface UserMealRepository extends JpaRepository<UserMealEntity, Long> {

    Set<UserMealEntity> findAllByUser(UserEntity user);

    Optional<UserMealEntity> findByUserAndId(UserEntity user, Long id);
}
