package com.amh.zenevent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("select c from Category c where c.isDeleted = false")
	List<Category> listCategory();

	@Query("select c from Category c where c.idCategory=:id and c.isDeleted = false")
	public Category getCategoryById(@Param("id") long id);

	@Query("select count(idCategory) from Category c where c.isDeleted = false") 
	long countCategory();

}
