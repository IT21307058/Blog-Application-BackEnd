package com.codewithbhanuka.blog.services;

import com.codewithbhanuka.blog.entities.User;
import com.codewithbhanuka.blog.payloads.UserDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
