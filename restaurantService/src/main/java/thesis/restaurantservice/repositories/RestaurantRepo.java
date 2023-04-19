package thesis.restaurantservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.restaurantservice.entities.Restaurant;

import java.util.Optional;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {


    Optional<Restaurant> findByName(String name);
}
