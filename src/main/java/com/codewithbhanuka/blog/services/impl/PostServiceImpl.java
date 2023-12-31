package com.codewithbhanuka.blog.services.impl;

import com.codewithbhanuka.blog.entities.Category;
import com.codewithbhanuka.blog.entities.Post;
import com.codewithbhanuka.blog.entities.User;
import com.codewithbhanuka.blog.exception.ResourceNotFoundException;
import com.codewithbhanuka.blog.payloads.PostDto;
import com.codewithbhanuka.blog.payloads.PostResponse;
import com.codewithbhanuka.blog.repositories.CategoryRepo;
import com.codewithbhanuka.blog.repositories.PostRepo;
import com.codewithbhanuka.blog.repositories.UserRepo;
import com.codewithbhanuka.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);


        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post deletepost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        this.postRepo.delete(deletepost);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = null;

        //using param decide asc or desc
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        // pagination
        //by default
//        int pageSize = 5;
//        int pageNumber = 1;

        // 1 page 5 records and also sort by post id using
        //this function ####Important
        //Sort.by(sortBy).descending() we can make descending order
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

//        List<Post> posts = this.postRepo.findAll();

        //get page records
        Page<Post> pagepost = this.postRepo.findAll(p);

        List<Post> allposts = pagepost.getContent();

        List<PostDto> postDtos = allposts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        //no of size in page
        postResponse.setPageNumber(pagepost.getNumber());
        postResponse.setPageSize(pagepost.getSize());
        postResponse.setTotalElements(pagepost.getTotalElements());
        postResponse.setTotalPages(pagepost.getTotalPages());
        postResponse.setLastPage(pagepost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post onepost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Posts", "post Id", postId));
        return this.modelMapper.map(onepost, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
            List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());


        return postDtos;
    }
}
