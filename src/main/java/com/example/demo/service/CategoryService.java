package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //CRUD(addCategory, getCategoryByName, getAllCategories, updateCategory, deleteCategory)
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryByName(String name){
        return categoryRepository.getCategoryByName(name);
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    //update

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
