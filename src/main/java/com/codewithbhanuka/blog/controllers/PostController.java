package com.codewithbhanuka.blog.controllers;

import com.codewithbhanuka.blog.entities.Post;
import com.codewithbhanuka.blog.payloads.ApiResponse;
import com.codewithbhanuka.blog.payloads.PostDto;
import com.codewithbhanuka.blog.services.PostService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    //create
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

     //get by user
     @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(
            @PathVariable Integer userId
     ){
        List<PostDto> posts = this.postService.getPostByUser(userId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
            @PathVariable Integer categoryId
    ){
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto onepost = this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(onepost, HttpStatus.OK);
    }

    // pagination added
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(
            //parameter
            @RequestParam (value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam (value = "pageSize", defaultValue = "5", required = false) Integer pageSize
     ){
        List<PostDto> allpost = this.postService.getAllPost(pageNumber, pageSize);

        return new ResponseEntity<List<PostDto>>(allpost, HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post Deleted Successfuly", true);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatepostDto = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatepostDto, HttpStatus.OK);
    }
}
