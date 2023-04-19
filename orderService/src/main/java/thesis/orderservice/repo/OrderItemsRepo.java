package thesis.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.orderservice.entities.OrderItems;

import java.util.Optional;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Long> {


    Optional<OrderItems> findAllByOrderId(String orderId);

}
