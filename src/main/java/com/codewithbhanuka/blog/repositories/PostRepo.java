package com.codewithbhanuka.blog.repositories;

import com.codewithbhanuka.blog.entities.Category;
import com.codewithbhanuka.blog.entities.Post;
import com.codewithbhanuka.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    // custom methods
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    // search using title
//    List<Post> findByTitleContaining(String title);

    //In one version later above code not working for all searches
    // but below code using for all other keword searching
    // but my version work above code but for learning
    //I code this

    // :key mean %key%
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);
}
