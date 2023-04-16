package thesis.authservice.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import thesis.authservice.config.UserAuthProvider;
import thesis.authservice.dto.CredentialsDto;
import thesis.authservice.dto.SignUpDto;
import thesis.authservice.dto.UserDto;
import thesis.authservice.service.UserService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private  UserAuthProvider userAuthProvider;


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){


        UserDto userDto = userService.register(signUpDto);

        userDto.setToken(userAuthProvider.generateToken(userDto.getEmail()));

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto){

     UserDto userDto = userService.login(credentialsDto);
     userDto.setToken(userAuthProvider.generateToken(userDto.getEmail()));
     return ResponseEntity.ok(userDto);
    }

    @GetMapping("/google/sign-in")
    public ResponseEntity<UserDto> signIn(Principal principal){

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
        String email = oauthToken.getPrincipal().getAttribute("email");

        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setToken(userAuthProvider.generateToken(email));
        return ResponseEntity.ok(userDto);
//        return oauthToken.getPrincipal().getAttributes();
    }


}
