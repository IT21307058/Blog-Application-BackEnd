package com.codewithbhanuka.blog.payloads;


import com.codewithbhanuka.blog.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    //comment list
    private Set<CommentDto> comments = new HashSet<>();



}
