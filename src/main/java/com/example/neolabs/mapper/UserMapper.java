package com.example.neolabs.mapper;

import com.example.neolabs.dto.UserDto;
import com.example.neolabs.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto UserEntityToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }
}
