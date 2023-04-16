package thesis.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.authservice.entities.UserCredentials;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredentials, Long> {


    Optional<UserCredentials> findByEmail(String login);


}
