package com.fiap.techChallenge.application.services;


import com.fiap.techChallenge.domain.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product productDto);

    Product updateProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts(String category);

    void deleteProduct(Long id);
}
