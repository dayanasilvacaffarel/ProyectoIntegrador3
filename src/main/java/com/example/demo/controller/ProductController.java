package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProductService;
import org.apache.velocity.exception.ResourceNotFoundException;
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
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product){
        if (categoryService.getCategoryById(product.getCategory().getId())!=null){
            return ResponseEntity.ok(productService.addProduct(product));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{productId}/addImage")
    public ResponseEntity<String> addImageToProduct(@PathVariable Long productId, @RequestBody Image image){
        Optional<ProductDTO> productDTO = productService.getProductById(productId);
        if (productDTO.isPresent()){
            productService.associateImageWithProduct(productDTO.get(), image);
            return ResponseEntity.ok("Image successfully associated with the product");
        } else {
            return ResponseEntity.notFound().build();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<ProductDTO> productFound = productService.getProductById(id);
        if (productFound.isPresent()){
            productService.deleteProduct(id);
            return ResponseEntity.ok("The Product was removed ID: " +id);
        }else {
            throw new ResourceNotFoundException("Delete error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            ProductDTO updatedProductDTO = productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(updatedProductDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}

