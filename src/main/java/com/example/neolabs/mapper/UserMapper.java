package com.example.neolabs.mapper;

import com.example.neolabs.dto.UserDto;
import com.example.neolabs.entity.User;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static UserDto userEntityToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setLastVisitDate(dateFormatter.format(user.getLastVisitDate()));
        userDto.setLastVisitTime(timeFormatter.format(user.getLastVisitDate()));
        return userDto;
    }

    public static List<UserDto> entityListToDtoList(List<User> users){
        return users.stream().map(UserMapper::userEntityToUserDto).collect(Collectors.toList());
    }
}
