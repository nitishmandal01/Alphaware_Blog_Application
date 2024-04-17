package com.blog.app.service;

import java.util.List;

import com.blog.app.payLoads.PostDto;


public interface PostService {

	//create 

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

	//update 

	PostDto updatePost(PostDto postDto, Integer postId);

	// delete

	void deletePost(Integer postId);
	
	//get all posts
	
	 List<PostDto> getAllPost();
	
	//get all post created on current day
	
	List<PostDto> getPostsCreatedToday();
	
	//get single post
	
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);


}
