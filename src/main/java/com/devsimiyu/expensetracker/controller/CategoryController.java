package com.devsimiyu.expensetracker.controller;

import java.util.List;

import com.devsimiyu.expensetracker.model.AddCategoryForm;
import com.devsimiyu.expensetracker.model.Category;
import com.devsimiyu.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public @ResponseBody List<Category> index() {
        return categoryService.getCategories();
    }

    @GetMapping("{id}")
    public @ResponseBody Category detail(@PathVariable("id") int id) {
        return categoryService.getCategory(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Category create(@RequestBody AddCategoryForm categoryForm) {
        Integer id = categoryService.addCategory(categoryForm);
        Category category = categoryService.getCategory(id);

        return category;
    }
}
