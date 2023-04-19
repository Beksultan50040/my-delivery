package thesis.orderservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import thesis.orderservice.dto.*;
import thesis.orderservice.entities.*;
import thesis.orderservice.exceptions.ApiError;
import thesis.orderservice.feign.RestaurantFeign;
import thesis.orderservice.feign.UserFeign;
import thesis.orderservice.mapper.OrderMapper;
import thesis.orderservice.repo.DriverRepo;
import thesis.orderservice.repo.OrderItemsRepo;
import thesis.orderservice.repo.OrderRepo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderItemsRepo orderItemsRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private RestaurantFeign restaurantFeign;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DriverRepo driverRepo;


    public Order createOrder(CreateOrderDto createOrderDto){

        Order order = new Order();

        order.setUserId(createOrderDto.getUserId());
        order.setRestaurantId(createOrderDto.getRestaurantId());
        order.setOrderTime(LocalDateTime.now());

        BigInteger total = createOrderDto.getOrderItems().stream()
                .map(item -> item.getPrice().multiply(BigInteger.valueOf(item.getQuantity())))
                .reduce(BigInteger.ZERO, BigInteger::add);
        order.setTotal(total);

        order.setDestinationAddress(createOrderDto.getDestinationAddress());

        List<Driver> drivers = driverRepo.findAllByStatus(Status.FREE);

        Driver freeDriver = drivers.stream().findFirst().orElse(new Driver());

        order.setDriverId(freeDriver.getId().toString());

        Order newOrder = orderRepo.save(order);

        freeDriver.setStatus(Status.BUSY);

        driverRepo.saveAndFlush(freeDriver);

        for (OrderItems item : createOrderDto.getOrderItems()) {
            item.setOrderId(newOrder);
            orderItemsRepo.save(item);
        }

        return order;

    }

    public OrderInfoDto getOrderInfoDto(String header, String userId){

        Order order = orderRepo.findByUserId(userId).orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND,
                "No order with user id = " + userId, new ArrayList<>()));

        UserData userData = userFeign.findById(header, userId);
        Restaurant restaurant = restaurantFeign.findById(header, order.getRestaurantId());

//        Menu menu = restaurant.getMenuItems().stream().filter(m -> m.getId() == Long.valueOf(order.getItems().getMenuId())).findFirst().orElse(null);

        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setUserName(userData.getFirstName());
        orderInfoDto.setDestinationAddress(order.getDestinationAddress());
        orderInfoDto.setOrderItemsDtos(orderMapper.toOrderItemsDto(order.getItems()));
        orderInfoDto.setRestaurantName(restaurant.getName());
        orderInfoDto.setRestaurantAddress(restaurant.getAddress());
        orderInfoDto.setTotal(order.getTotal());

        return orderInfoDto;


    }

//    public OrderInfoDto createOrderInfoDto(String userId){
//
//
//
//    }
}
