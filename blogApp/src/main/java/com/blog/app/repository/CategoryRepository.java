package com.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
