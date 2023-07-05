package com.fiap.techChallenge.application.ports.out;

import com.fiap.techChallenge.application.core.domain.Product;

import java.util.List;

public interface ProductOutPort {
    public Product saveProduct(Product product);

    public Product updateProduct(Product product);

    public Product getProductById(Long id);

    public List<Product> getAllProducts(String category);

    public void deleteProduct(Long id);
}
