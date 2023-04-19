package thesis.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.orderservice.entities.Driver;
import thesis.orderservice.entities.Status;

import java.util.List;
import java.util.Optional;

public interface DriverRepo extends JpaRepository<Driver, Long> {

    List<Driver> findAllByStatus(Status status);
}
