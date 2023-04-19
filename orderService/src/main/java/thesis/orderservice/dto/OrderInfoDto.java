package thesis.orderservice.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class OrderInfoDto {

    private String userName;
    private String restaurantName;
    private String restaurantAddress;

    private List<OrderItemsDto> orderItemsDtos;
    private BigInteger total;
    private String destinationAddress;
}
