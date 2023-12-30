package com.codewithbhanuka.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private String imageName;

    //date be added in automatically
    private Date addedDate;

    // dto class eka thmi danna oni methnata
    private CategoryDto category;
    private UserDto user;



}
