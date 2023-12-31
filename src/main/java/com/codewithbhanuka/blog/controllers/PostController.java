package com.codewithbhanuka.blog.controllers;

import com.codewithbhanuka.blog.config.AppConstants;
import com.codewithbhanuka.blog.entities.Post;
import com.codewithbhanuka.blog.payloads.ApiResponse;
import com.codewithbhanuka.blog.payloads.PostDto;
import com.codewithbhanuka.blog.payloads.PostResponse;
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

    // pagination added hi
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            //parameter
            @RequestParam (value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam (value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam (value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam (value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
     ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
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


    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            @PathVariable String keywords
    ){
        List<PostDto> result = this.postService.searchPost(keywords);

        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }
}
