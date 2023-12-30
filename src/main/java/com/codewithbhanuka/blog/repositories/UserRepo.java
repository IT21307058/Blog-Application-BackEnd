package com.codewithbhanuka.blog.repositories;

import com.codewithbhanuka.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
