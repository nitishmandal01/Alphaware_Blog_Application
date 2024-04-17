package com.blog.app.payLoads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private String title;
	
	private String content;
	
	
	private Date addedDate;	
	
	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();

}
