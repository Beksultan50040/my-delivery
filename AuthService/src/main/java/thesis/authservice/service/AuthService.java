package thesis.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thesis.authservice.config.UserAuthProvider;

@Service
public class AuthService {


    @Autowired
    private UserAuthProvider userAuthProvider;

    public void validateToken(String token) {
        userAuthProvider.validateToken(token);
    }


}
