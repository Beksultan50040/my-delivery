package thesis.authservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thesis.authservice.config.UserAuthProvider;
import thesis.authservice.dto.CredentialsDto;
import thesis.authservice.dto.SignUpDto;
import thesis.authservice.dto.UserDto;
import thesis.authservice.entities.UserCredentials;
import thesis.authservice.entities.UserData;
import thesis.authservice.exceptions.ApiError;
import thesis.authservice.mapper.UserMapper;
import thesis.authservice.repository.UserDataRepository;
import thesis.authservice.repository.UserRepository;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private final UserDataRepository userDataRepository;

//
//    @Autowired
//    private EmailService emailService;

//    @Value("${password.reset}")
//    String link;


    public UserDto login(CredentialsDto credentialsDto) {
        UserCredentials userCredentials = userRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND, "Unknown user", new ArrayList<>()));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), userCredentials.getPassword())) {
            return UserMapper.INSTANCE.toUserDto(userCredentials);
        }
        throw new ApiError(HttpStatus.BAD_REQUEST, "Invalid password", new ArrayList<>());
    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<UserCredentials> optionalUser = userRepository.findByEmail(signUpDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new ApiError(HttpStatus.BAD_REQUEST, "Login already exists", new ArrayList<>());
        }

        UserCredentials userCredentials = UserMapper.INSTANCE.signUpToUser(signUpDto);
        userCredentials.setPassword(Arrays.toString(signUpDto.getPassword().toCharArray()));
        userCredentials.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));

        userDataRepository.save(new UserData(userCredentials.getId(), userCredentials.getFirstName(), userCredentials.getLastName(),
                userCredentials.getEmail(), userCredentials.getGender(),
                userCredentials.getAge()));

        return UserMapper.INSTANCE.toUserDto(userRepository.save(userCredentials));

    }

    public void validateToken(String token) {
        authService.validateToken(token);
    }

    public UserDto findByLogin(String login) {
        UserCredentials userCredentials = userRepository.findByEmail(login)
                .orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND, "Unknown user", new ArrayList<>()));
        return UserMapper.INSTANCE.toUserDto(userCredentials);
    }

    public UserData findById(String id){

        return userDataRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND, "Unknown user", new ArrayList<>()));
    }




//    public String resetPassword(String email){
//
//        String body = """
//            Пожалуйста используйте ссылку ниже для установки нового пароля, но сперва замените * на ваши данные
//            потом скопируйте и вставьте в поисковике браузера и нажмите enter, готово
//                    """ + "\n" + link;
//
//        emailService.sendMail(email, body);
//        return "Ok";
//    }

//    public String insertNewPassword(String password, String email){
//
//        UserCredentials userCredentials = userRepository.findByEmail(email)
//                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//
//        userCredentials.setPassword(passwordEncoder.encode(CharBuffer.wrap(password)));
//        userRepository.save(userCredentials);
//
//        return "Ok";
//    }

}
