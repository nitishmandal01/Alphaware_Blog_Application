package com.blog.app.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.exception.CategoryException;
import com.blog.app.exception.PostException;
import com.blog.app.exception.UserException;
import com.blog.app.model.Category;
import com.blog.app.model.Post;
import com.blog.app.model.User;
import com.blog.app.payLoads.PostDto;
import com.blog.app.repository.CategoryRepository;
import com.blog.app.repository.PostRepository;
import com.blog.app.repository.UserRepository;
import com.blog.app.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

    	User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new UserException("USer Not Present in database"));

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category Not Present in database"));

        Post post =  this.modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new PostException("Post Not presnt in database"));

        Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId()).get();

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(category);


        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new PostException("Post Not presnt in database"));

        this.postRepo.delete(post);

    }


    @Override
    public List<PostDto> getAllPost() {
        List<Post> allPosts = this.postRepo.findAll();
        
        return allPosts.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    
    @Override
    public List<PostDto> getPostsCreatedToday() {
        LocalDate today = LocalDate.now();

        List<Post> allPosts = postRepo.findAll();

        List<PostDto> postsCreatedToday = allPosts.stream()
                .filter(post -> toLocalDate(post.getAddedDate()).isEqual(today))
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postsCreatedToday;
    }
    // Method to convert Date to LocalDate
    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    @Override
    public PostDto getPostById(Integer postId) {
    	Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new PostException("Post Not presnt in database"));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

    	 Category cat = this.categoryRepo.findById(categoryId)
                 .orElseThrow(() -> new CategoryException("Category Not Present in database"));
        List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {


    	User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new UserException("USer Not Present in database"));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }


}