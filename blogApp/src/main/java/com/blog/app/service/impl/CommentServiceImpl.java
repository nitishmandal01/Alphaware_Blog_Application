package com.blog.app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.exception.CommentException;
import com.blog.app.exception.PostException;
import com.blog.app.model.Comment;
import com.blog.app.model.Post;
import com.blog.app.payLoads.CommentDto;
import com.blog.app.repository.CommentRepository;
import com.blog.app.repository.PostRepository;
import com.blog.app.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		  Post post = this.postRepo.findById(postId)
	                .orElseThrow(() -> new PostException("Post Not presnt in database"));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);

		Comment savedComment = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new CommentException("Comment does Not presnt in database"));
		this.commentRepo.delete(com);
	}

}