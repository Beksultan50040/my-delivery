package thesis.authservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_data")
public class UserData {


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
    @Column(nullable = false)
    @Size(max = 100)
    private String email;

    @NotNull
    private String gender;


    @NotNull
    private Integer age;



//    @OneToOne
//    @EqualsAndHashCode.Exclude @ToString.Exclude
//    @JoinColumn(name = "user_credentials_id")
//    private UserCredentials userCredentials;
}
