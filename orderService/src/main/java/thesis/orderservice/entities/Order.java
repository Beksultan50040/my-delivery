package thesis.orderservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "restaurant_id")
    private String restaurantId;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> items;


    @Column(name = "order_Time")
    private LocalDateTime orderTime;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "order_completed")
    private LocalDateTime orderCompleted;

    private BigInteger total;

    private String driverId;

}
