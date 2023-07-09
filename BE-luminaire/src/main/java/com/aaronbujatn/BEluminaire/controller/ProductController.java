package com.aaronbujatn.BEluminaire.controller;

import com.aaronbujatn.BEluminaire.model.Product;
import com.aaronbujatn.BEluminaire.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return new ResponseEntity<>(productService.update(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getAllProductByCategory(@PathVariable("category") String category){
        return new ResponseEntity<>(productService.getAllProductByCategory(category), HttpStatus.OK);
    }
}
