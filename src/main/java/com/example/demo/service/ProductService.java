package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


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

    public List<ProductDTO> getProducts(){
        List<Product> productFound = productRepository.findAll();//Va al repository a buscar los productos
        List<ProductDTO> productDTOList = new ArrayList<>();//Array vacio que se va a usar para almacenar los productos listados
        for (Product product: productFound){
            productDTOList.add(productToproductDTO(product));
        }
        return productDTOList;
    }

    public Optional<ProductDTO> getProductById(Long id){
        Optional<Product> productFound=productRepository.findById(id);
        if (productFound.isPresent()){
            return Optional.of(productToproductDTO(productFound.get()));
        }else {
            return Optional.empty();
        }
    }

     public void deleteProduct(Long id){
        productRepository.deleteById(id);
     }
    public ProductDTO updateProduct(Long productId, Product updatedProductDTO) {
        // Busca el producto existente en la base de datos por su ID
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();

            // Actualiza las propiedades del producto con los valores del DTO
            existingProduct.setName(updatedProductDTO.getName());
            existingProduct.setDescription(updatedProductDTO.getDescription());
            existingProduct.setActive(updatedProductDTO.getActive());
            existingProduct.setPrice(updatedProductDTO.getPrice());
            existingProduct.setBrand(updatedProductDTO.getBrand());
            existingProduct.setModel(updatedProductDTO.getModel());

            // Guarda el producto actualizado en la base de datos
            Product updatedProduct = productRepository.save(existingProduct);

            // Convierte el producto actualizado en un DTO y devuélvelo
            return productToproductDTO(updatedProduct);
        } else {
            // Maneja el caso en el que el producto no se encuentra en la base de datos
            throw new ResourceNotFoundException("Product not found with ID: " + productId);
        }
    }


}
