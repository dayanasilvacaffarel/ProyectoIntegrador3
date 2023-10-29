package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product){
        if (categoryService.getCategoryById(product.getCategory().getId())!=null){
            return ResponseEntity.ok(productService.addProduct(product));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        Optional<ProductDTO> productFound = productService.getProductById(id);
        if (productFound.isPresent()){
            return ResponseEntity.ok(productFound.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}

