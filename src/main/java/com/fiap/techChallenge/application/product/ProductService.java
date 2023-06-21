package com.fiap.techChallenge.application.product;

import com.fiap.techChallenge.domain.Product;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);

    public Product updateProduct(Product product);

    public Product getProductById(Long id);

    public List<Product> getAllProducts(String category);

    public void deleteProduct(Long id);
}
