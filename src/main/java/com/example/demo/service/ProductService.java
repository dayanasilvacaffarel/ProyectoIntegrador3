package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    private ProductDTO productToproductDTO(Product product){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setActive(product.getActive());
        productDTO.setPrice(product.getPrice());
        productDTO.setBrand(product.getBrand());
        productDTO.setModel(product.getModel());
        productDTO.setCategoryId(product.getCategory().getId());
        return productDTO;
    }

    private Product productDtoToProduct(ProductDTO productDTO){
        Product product = new Product();
        Category category = new Category();
        category.setId(productDTO.getCategoryId());
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setActive(productDTO.getActive());
        product.setPrice(productDTO.getPrice());
        product.setBrand(productDTO.getBrand());
        product.setModel(productDTO.getModel());
        product.setImages(productDTO.getImages());

        return product;

    }


    public ProductDTO addProduct(Product product){

        Product productSaved = productRepository.save((product));
        return productToproductDTO(productSaved);
    }

//    public List<Product> getProducts(){
//        return productRepository.findAll();
//    }
//     public void deleteProduct(Long id){
//        productRepository.deleteById(id);
//     }
//
//     public void updateProduct(Product product){
//        productRepository.save(product);
//     }

}
