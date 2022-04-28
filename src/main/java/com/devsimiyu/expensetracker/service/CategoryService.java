package com.devsimiyu.expensetracker.service;

import java.util.List;

import com.devsimiyu.expensetracker.model.AddCategoryForm;
import com.devsimiyu.expensetracker.model.Category;
import com.devsimiyu.expensetracker.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    
    public List<Category> getCategories() {
        return categoryRepo.getCategoryList();
    }

    public Category getCategory(int id) {
        return categoryRepo.findCategoryById(id);
    }

    public Integer addCategory(AddCategoryForm categoryForm) {
        return categoryRepo.saveCategory(categoryForm.getName());
    }
}
