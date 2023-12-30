package com.codewithbhanuka.blog.services.impl;

import com.codewithbhanuka.blog.entities.User;
import com.codewithbhanuka.blog.exception.ResourceNotFoundException;
import com.codewithbhanuka.blog.payloads.UserDto;
import com.codewithbhanuka.blog.repositories.UserRepo;
import com.codewithbhanuka.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//you must use @service annotation for service impl class
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    //@Bean redirect to the create object in the model mapper method

    @Override
    public UserDto createUser(UserDto userDto) {
        //convert
        User user = this.dtoToUser(userDto);

        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        //find user not user in system handle exception
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateduser = this.userRepo.save(user);

        //convert user -> userdto
        UserDto userDto1 = this.userToDto(updateduser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();

//        Streams in Java provide a way to work with collections of
//        objects in a functional style, allowing you to perform
//        aggregate operations like filter, map, reduce, and more.
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        this.userRepo.delete(user);
    }

    //dto to user mannullay covert
    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);

        //mannual method
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());

        return user;
    }

    //user to dto
    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);



//        userDto.setId(user.getId());
//        userDto.setEmail(user.getEmail());
//        userDto.setName(user.getName());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());

        return userDto;
    }
}
