package thesis.authservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    @Size(max = 100)
    private String email;

    @NotNull
    private String gender;


    @NotNull
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Roles roles = Roles.ROLE_USER;

    @NotEmpty
    @Column(nullable = false)
    @Size(max = 100)
    private String password;

}