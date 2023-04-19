package thesis.orderservice.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class OrderItemsDto {

    private String menuName;
    private int quantity;
    private BigInteger price;
}
