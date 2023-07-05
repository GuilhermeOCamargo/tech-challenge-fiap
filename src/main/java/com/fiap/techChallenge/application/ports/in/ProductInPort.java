package com.fiap.techChallenge.application.ports.in;

import com.fiap.techChallenge.application.core.domain.Product;
import com.fiap.techChallenge.application.core.exceptions.AlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;

import java.util.List;

public interface ProductInPort {

    Product saveProduct(Product product) throws AlreadyExistsException;

    Product updateProduct(Product product) throws NotFoundException;

    Product getProductById(Long id) throws NotFoundException;

    List<Product> getAllProducts(String category) throws NotFoundException;

    void deleteProduct(Long id) throws NotFoundException;
}
