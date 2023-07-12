package com.aaronbujatn.BEluminaire.service;

import com.aaronbujatn.BEluminaire.exception.ProductNotFoundException;
import com.aaronbujatn.BEluminaire.model.Product;
import com.aaronbujatn.BEluminaire.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product){
        product.setCreatedAt(LocalDate.now());
        return productRepository.save(product);
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id: " + id + " was not found!"));
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product update(Long id, Product product){
        Product updatedProduct = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id: " + id + " was not found!"));
        List<String> imageUrl = new ArrayList<>();
        imageUrl = product.getImageUrl();

        updatedProduct.setBrand(product.getBrand());
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setImageUrl(imageUrl);

        return productRepository.save(updatedProduct);
    }

    public String delete(Long id){
        productRepository.deleteById(id);
        return "Product ID : " + id + " was successfully deleted!";
    }

    public List<Product> getAllProductByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public List<Product> randomProducts(){
        return productRepository.findRandomProducts();
    }

}
