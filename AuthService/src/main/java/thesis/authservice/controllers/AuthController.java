package thesis.authservice.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import thesis.authservice.config.UserAuthProvider;
import thesis.authservice.dto.CredentialsDto;
import thesis.authservice.dto.SignUpDto;
import thesis.authservice.dto.UserDto;
import thesis.authservice.entities.UserData;
import thesis.authservice.service.UserService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthProvider userAuthProvider;


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {


        UserDto userDto = userService.register(signUpDto);

        userDto.setToken(userAuthProvider.generateToken(userDto.getEmail()));

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {

        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthProvider.generateToken(userDto.getEmail()));
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/google/sign-in")
    public ResponseEntity<UserDto> signIn(Principal principal) {

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
        String email = oauthToken.getPrincipal().getAttribute("email");

        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setToken(userAuthProvider.generateToken(email));
        return ResponseEntity.ok(userDto);
//        return oauthToken.getPrincipal().getAttributes();
    }


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Secured Endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserData> findById(@PathVariable String id) {

        return ResponseEntity.ok(userService.findById(id));

    }


}
