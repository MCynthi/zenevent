package com.amh.zenevent.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amh.zenevent.entities.Category;
import com.amh.zenevent.repository.CategoryRepository;
import com.amh.zenevent.service.CategoryService;

@Controller
@RequestMapping("/gestionnaire")
public class CategoryFrontend {
	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String listCategory(Model model) {
		List<Category> category = categoryService.listCategory();
		model.addAttribute("listCategory", category);
		model.addAttribute("category", new Category());
		return "category/listCategory";
	}

	@RequestMapping(value = "/categories/saveCategory", method = RequestMethod.POST)
	public String saveCategory(Category category) {
		categoryService.addCategory(category);
		return "redirect:/gestionnaire/categories";
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody Category category) {

		if (categoryService.isCategoryExist(id)) {
			categoryService.updateCategory(id, category);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public String updateVisitor(Category category) {
		long id = category.getIdCategory();
		categoryService.updateCategory(id, category);
		return "redirect:/gestionnaire/categories";
	}

	@RequestMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable long id) {
		Category category = categoryRepository.getCategoryById(id);
		category.setIsDeleted(true);
		categoryRepository.save(category);
		return "redirect:/gestionnaire/categories";
	}

	@GetMapping("/gestionnaire/categories/count")
	public long countCategory() {
		return categoryService.countCategory();
	}

}
