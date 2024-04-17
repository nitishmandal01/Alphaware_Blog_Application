package com.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.model.Comment;

public interface CommentRepository  extends JpaRepository<Comment	, Integer> {

}