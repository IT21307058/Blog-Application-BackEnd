package com.codewithbhanuka.blog.repositories;

import com.codewithbhanuka.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
