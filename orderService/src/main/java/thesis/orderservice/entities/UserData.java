package thesis.orderservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class UserData {


    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank

    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private String gender;


    @NotNull
    private Integer age;

}
