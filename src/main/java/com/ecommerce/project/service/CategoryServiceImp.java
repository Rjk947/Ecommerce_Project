package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category categoryToBeDeleted = categoryRepository.findById(categoryId).orElseThrow(() -> new
                    ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", categoryId)));

        categoryRepository.delete(categoryToBeDeleted);
        return "Category deleted successfully with category id: " + categoryId;
    }

    @Override
    public String updateCategory(Category categoryToBeUpdated, Long categoryId) {
        Category updatedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new
                ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", categoryId)));

        updatedCategory.setCategoryName(categoryToBeUpdated.getCategoryName());
        categoryRepository.save(updatedCategory);
        return "Category updated successfully with category id: " + categoryId;
    }
}
