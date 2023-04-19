package thesis.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.orderservice.entities.Order;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {


    Optional<Order> findByUserId(String userId);
}
