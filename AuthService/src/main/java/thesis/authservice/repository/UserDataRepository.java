package thesis.authservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import thesis.authservice.entities.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long > {

    UserData findById(long id);


//    @Query("SELECT u, m FROM UserData u JOIN UserMoviePreference p ON u.id = p.user JOIN Movie m ON p.movie = m.id WHERE u.id = :id")
//    UserData findUserAndMoviePreferencesById(@Param("id") Long id);
}
