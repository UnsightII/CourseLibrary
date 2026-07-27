package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Category;
import com.application.courselibrary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String findAllCategory(Model model){
        List <Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);
        return "categories";
    }

    @GetMapping("/update-category/{id}")
        public String updateCategory(@PathVariable long id, Model model) {
            Category category = categoryService.findCategoryById(id);

            model.addAttribute("category", category);
            return "update-category";
        }

    @PostMapping("/save-update-category/{id}")
    public String saveUpdateCategory(@PathVariable long id,Category category, BindingResult result) {

        if (result.hasErrors()) {
            return "update-category";
        }

        categoryService.updateCategory(category);

        return "redirect:/categories";
    }

    @GetMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult result) {

        if (result.hasErrors()) {
            return "add-category";
        }

        categoryService.createCategory(category);

        return "redirect:/categories";
    }

}
