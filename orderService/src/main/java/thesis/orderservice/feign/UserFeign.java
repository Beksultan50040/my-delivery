package thesis.orderservice.feign;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import thesis.orderservice.entities.UserData;

@FeignClient(name = "test", url = "http://AUTH-SERVICE:8081/auth")
public interface UserFeign {

    @GetMapping("/{id}")
    UserData findById(@RequestHeader("Authorization") String bearerToken, @PathVariable String id);

}
