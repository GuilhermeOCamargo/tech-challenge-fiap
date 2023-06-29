package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.infra.inbound.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto);

    ProductDto getProductById(Long id);

    List<ProductDto> getAllProducts(String category);

    void deleteProduct(Long id);
}
