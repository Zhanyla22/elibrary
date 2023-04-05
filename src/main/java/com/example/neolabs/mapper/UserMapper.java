package com.example.neolabs.mapper;

import com.example.neolabs.dto.UserDto;
import com.example.neolabs.entity.User;
import com.example.neolabs.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setLastVisitDate(DateUtil.dateFormatter.format(user.getLastVisitDate()));
        userDto.setLastVisitTime(DateUtil.timeFormatter.format(user.getLastVisitDate()));
        return userDto;
    }

    public static List<UserDto> entityListToDtoList(List<User> users){
        return users.stream().map(UserMapper::entityToDto).collect(Collectors.toList());
    }
}
