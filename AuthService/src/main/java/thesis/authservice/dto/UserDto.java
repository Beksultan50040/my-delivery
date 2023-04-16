package thesis.authservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String gender;


    private Integer age;

    private String roles;
    private String token;

}
