package thesis.orderservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "vehicle_registration_number")
    private String vehicleRegistrationNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @Enumerated(EnumType.STRING)
    private Status status;




}
