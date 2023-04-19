package thesis.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class Restaurant {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private String phoneNumber;

    private String description;

    private LocalDateTime createdAt;


    private List<Menu> menuItems = new ArrayList<>();

}