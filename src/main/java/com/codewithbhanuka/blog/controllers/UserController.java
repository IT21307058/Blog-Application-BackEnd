package com.codewithbhanuka.blog.controllers;

import com.codewithbhanuka.blog.payloads.ApiResponse;
import com.codewithbhanuka.blog.payloads.UserDto;
import com.codewithbhanuka.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
//auto relaod happen in project spring boot tool install
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto updatedUserDto = this.userService.updateUser(userDto, uid);

        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping ("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);

        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping ("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping ("/{userId}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable("userId") Integer uid){

        return ResponseEntity.ok(this.userService.getUserById(uid));
    }
}
