package thesis.restaurantservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.restaurantservice.entities.Menu;

public interface MenuRepo extends JpaRepository<Menu, Long> {


}
