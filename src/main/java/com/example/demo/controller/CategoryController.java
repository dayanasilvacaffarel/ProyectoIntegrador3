package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Optional<Category> categoriaBuscada = categoryService.getCategoryById(id);
        if (categoriaBuscada.isPresent()){
            return ResponseEntity.ok(categoriaBuscada.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name){
        Optional<Category> categoriaBuscada = categoryService.getCategoryByName(name);
        if (categoriaBuscada.isPresent()){
            return ResponseEntity.ok(categoriaBuscada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateCategory(@RequestBody Category category) throws ResourceNotFoundException{
//        Optional<Category> categoriaBuscada = categoryService.getAllCategories(category.getId());
//        if (categoriaBuscada.isPresent()){
//            categoryService.updateCategory(category);
//            return ResponseEntity.ok("Category was Updated -"+category.getName());
//        }else {
//            throw  new ResourceNotFoundException("Category was not found");
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("The category with the ID was deleted:" +id);
    }
}
