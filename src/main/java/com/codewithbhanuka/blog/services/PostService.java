package com.codewithbhanuka.blog.services;

import com.codewithbhanuka.blog.entities.Post;
import com.codewithbhanuka.blog.payloads.PostDto;
import com.codewithbhanuka.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);
}
