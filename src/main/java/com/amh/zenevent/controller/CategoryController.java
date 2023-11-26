package com.amh.zenevent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amh.zenevent.entities.Category;
import com.amh.zenevent.repository.CategoryRepository;
import com.amh.zenevent.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping(value = "/categories")
	public List<Category> listCategory(){
		return categoryService.listCategory();
	}
	
	@GetMapping(value = "/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable long id){
		Category category = categoryService.getCategoryById(id);
		if (category != null) {
			return new ResponseEntity<Category>(category,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		try {
			category.setIsDeleted(false);
			categoryService.addCategory(category);
			return new ResponseEntity<Category>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/categories/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody Category category){
		
		if (categoryService.isCategoryExist(id)) {
			categoryService.updateCategory(id,category);
			return new ResponseEntity<>(HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/categorys/{id}")
	public void deleteCategory(@PathVariable long id) {
		Category category = categoryRepository.getCategoryById(id);
		category.setIsDeleted(true);
		categoryRepository.save(category);
	}
	
	@GetMapping("/categories/count")
	public long countCategory() {
		return categoryService.countCategory();
	}


}
