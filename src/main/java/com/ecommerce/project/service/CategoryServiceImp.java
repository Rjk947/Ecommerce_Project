package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1l;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category categoryToBeDeleted = categories.stream().filter(category -> category.getCategoryId()
                    .equals(categoryId)).findFirst().orElseThrow(() -> new
                    ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", categoryId)));

        categories.remove(categoryToBeDeleted);
        return "Category deleted successfully with category id: " + categoryId;
    }

    @Override
    public String updateCategory(Category categoryToBeUpdated, Long categoryId) {
        Category updatedCategory = categories.stream().filter(category -> category.getCategoryId()
                .equals(categoryId)).findFirst().orElseThrow(() -> new
                ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", categoryId)));

        updatedCategory.setCategoryName(categoryToBeUpdated.getCategoryName());
        return "Category updated successfully with category id: " + categoryId;
    }
}
