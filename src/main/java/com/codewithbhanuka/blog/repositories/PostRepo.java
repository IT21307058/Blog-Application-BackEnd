package com.codewithbhanuka.blog.repositories;

import com.codewithbhanuka.blog.entities.Category;
import com.codewithbhanuka.blog.entities.Post;
import com.codewithbhanuka.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    // custom methods
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
