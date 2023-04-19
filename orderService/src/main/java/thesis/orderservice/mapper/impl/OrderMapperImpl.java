package thesis.orderservice.mapper.impl;

import org.springframework.stereotype.Service;
import thesis.orderservice.dto.OrderInfoDto;
import thesis.orderservice.dto.OrderItemsDto;
import thesis.orderservice.entities.OrderItems;
import thesis.orderservice.mapper.OrderMapper;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderMapperImpl implements OrderMapper {



    @Override
    public List<OrderItemsDto> toOrderItemsDto(List<OrderItems> orderItems) {

        List<OrderItemsDto> orderItemsDtos = new ArrayList<>();

        orderItems.stream().forEach(o -> {

            OrderItemsDto orderItemsDto = new OrderItemsDto();

            orderItemsDto.setMenuName(o.getMenuName());
            orderItemsDto.setQuantity(o.getQuantity());
            orderItemsDto.setPrice(o.getPrice());

            orderItemsDtos.add(orderItemsDto);
        });

        return orderItemsDtos;
    }
}
