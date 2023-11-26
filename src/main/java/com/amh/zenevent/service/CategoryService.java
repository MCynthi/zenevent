package com.amh.zenevent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.Category;
import com.amh.zenevent.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> listCategory() {		
		return categoryRepository.listCategory();
	}

	public Category getCategoryById(long id) {
		return categoryRepository.getCategoryById(id);
	}

	public void addCategory(Category category) {
		category.setIsDeleted(false);
		categoryRepository.save(category);
	}

	public boolean isCategoryExist(long id) {
		return categoryRepository.existsById(id);
	}

	public void updateCategory(long id, Category category) {
		Category checkCategory = categoryRepository.getCategoryById(id);
		if (checkCategory != null) {
			checkCategory.setNomCategorie(category.getNomCategorie());
		}
		categoryRepository.save(checkCategory);		
	}

	public long countCategory() {
		return categoryRepository.countCategory();
	}

}
