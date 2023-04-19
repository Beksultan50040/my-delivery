package thesis.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Data
public class Menu {


    @NotNull
    private Long id;


    private Restaurant restaurant;


    @NotNull
    @NotBlank
    private String name;

    private String description;

    @NotNull
    @NotBlank
    private BigInteger price;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}