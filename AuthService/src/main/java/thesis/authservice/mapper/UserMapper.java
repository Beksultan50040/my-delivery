package thesis.authservice.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import thesis.authservice.dto.SignUpDto;
import thesis.authservice.dto.UserDto;
import thesis.authservice.entities.UserCredentials;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(UserCredentials userCredentials);

    @Mapping(target = "password", ignore = true)
    UserCredentials signUpToUser(SignUpDto signUpDto);




}