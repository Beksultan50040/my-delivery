package thesis.orderservice.dto;


import lombok.Data;
import thesis.orderservice.entities.OrderItems;

import java.util.List;

@Data
public class CreateOrderDto {


    private String userId;
    private String restaurantId;
    private String menuName;

    private String destinationAddress;

    List<OrderItems> orderItems;
}
