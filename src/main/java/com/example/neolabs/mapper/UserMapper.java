package com.example.neolabs.mapper;

import com.example.neolabs.dto.response.UserResponse;
import com.example.neolabs.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserResponse entityToDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .login(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .group(user.getCourse().getGroupss()+" "+user.getCourse().getName())
                .build();
    }

    public static List<UserResponse> entityListToDtoList(List<User> users) {
        return users.stream().map(UserMapper::entityToDto).collect(Collectors.toList());
    }
}
