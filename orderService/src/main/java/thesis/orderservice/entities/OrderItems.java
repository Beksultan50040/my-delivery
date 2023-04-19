package thesis.orderservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;


@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItems {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orderId;


    @Column(name = "menu_name")
    private String menuName;

    private int quantity;

    private BigInteger price;


}
