package com.blog.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.exception.CategoryException;
import com.blog.app.model.Category;
import com.blog.app.payLoads.CategoryDto;
import com.blog.app.repository.CategoryRepository;
import com.blog.app.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
		Category category = this.dtoToCategory(catDto);
		Category savedcategory = this.catRepo.save(category);
		return this.categoryToDto(savedcategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new CategoryException("Category not present in database"));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedcat = this.catRepo.save(cat);

		return this.modelMapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new CategoryException("Category not present in database"));
		this.catRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new CategoryException("Category not present in database"));

		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories = this.catRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return catDtos;
	}

	public Category dtoToCategory(CategoryDto catDto) {
		Category cat = this.modelMapper.map(catDto, Category.class);
		return cat;
	}

	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	
}