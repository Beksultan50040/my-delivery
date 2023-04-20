package thesis.orderservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import thesis.orderservice.dto.Restaurant;

@FeignClient(name = "test", url = "http://RESTAURANT-SERVICE:8082/restaurant")
public interface RestaurantFeign {

    @GetMapping("/{id}")
    Restaurant findById(@RequestHeader("Authorization") String bearerToken, @PathVariable String id);
}
