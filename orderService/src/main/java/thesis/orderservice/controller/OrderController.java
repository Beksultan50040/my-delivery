package thesis.orderservice.controller;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thesis.orderservice.dto.CreateOrderDto;
import thesis.orderservice.dto.OrderInfoDto;
import thesis.orderservice.entities.Order;
import thesis.orderservice.entities.UserData;
import thesis.orderservice.feign.UserFeign;
import thesis.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private UserFeign userFeign;


    @PostMapping("")
    public ResponseEntity<Order> saveOrder(@RequestBody CreateOrderDto createOrderDto){

        return ResponseEntity.ok(orderService.createOrder(createOrderDto));
    }

//    @GetMapping("/{id}")
//    public UserData findById(HttpServletRequest httpServletRequest, @PathVariable String id){
//
//        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
//        return userFeign.findById(header, id);
//    }

    @GetMapping("/{id}")
    public OrderInfoDto findOrderByUserId(HttpServletRequest httpServletRequest, @PathVariable String id){

        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");

        return orderService.getOrderInfoDto(header, id);

    }

}
