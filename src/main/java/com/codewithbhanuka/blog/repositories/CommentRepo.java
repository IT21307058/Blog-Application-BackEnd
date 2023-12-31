package com.codewithbhanuka.blog.repositories;

import com.codewithbhanuka.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
