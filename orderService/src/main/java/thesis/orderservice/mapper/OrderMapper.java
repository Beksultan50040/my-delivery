package thesis.orderservice.mapper;

import thesis.orderservice.dto.OrderItemsDto;
import thesis.orderservice.entities.OrderItems;

import java.util.List;

public interface OrderMapper {


    List<OrderItemsDto> toOrderItemsDto(List<OrderItems> orderItems);
}
