package thesis.orderservice.controller;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thesis.orderservice.dto.CreateOrderDto;
import thesis.orderservice.dto.OrderInfoDto;
import thesis.orderservice.entities.Driver;
import thesis.orderservice.entities.Order;
import thesis.orderservice.entities.UserData;
import thesis.orderservice.feign.UserFeign;
import thesis.orderservice.repo.DriverRepo;
import thesis.orderservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private DriverRepo driverRepo;



    @PostMapping("")
    public ResponseEntity<Order> saveOrder(@RequestBody CreateOrderDto createOrderDto){

        return ResponseEntity.ok(orderService.createOrder(createOrderDto));
    }

    @GetMapping("/{id}")
    public OrderInfoDto findOrderByUserId(HttpServletRequest httpServletRequest, @PathVariable String id){

        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");

        return orderService.getOrderInfoDto(header, id);

    }

    @GetMapping("/driver")
    public List<Driver> getDrivers(){

        return driverRepo.findAll();

    }

}
