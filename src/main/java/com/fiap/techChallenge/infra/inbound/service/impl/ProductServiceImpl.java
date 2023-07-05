package com.fiap.techChallenge.infra.inbound.service.impl;

import com.fiap.techChallenge.application.core.domain.Product;
import com.fiap.techChallenge.application.core.exceptions.AlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.in.ProductInPort;
import com.fiap.techChallenge.infra.inbound.dto.ProductDto;
import com.fiap.techChallenge.infra.inbound.exception.ResourceAlreadyExists;
import com.fiap.techChallenge.infra.inbound.exception.ResourceNotFoundException;
import com.fiap.techChallenge.infra.inbound.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductInPort port;

    @Override
    public ProductDto saveProduct(@Valid ProductDto productDto) {
        try {
            Product product = port.saveProduct(productDto.toDomain());
            return ProductDto.of(product);
        } catch (AlreadyExistsException e) {
            throw new ResourceAlreadyExists(e.getMessage());
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        try {
            Product product = port.updateProduct(productDto.toDomain());
            return ProductDto.of(product);
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public ProductDto getProductById(Long id) {
        try {
            Product product = port.getProductById(id);
            return ProductDto.of(product);
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<ProductDto> getAllProducts(String category) {
        try {
            List<Product> products = port.getAllProducts(category);
            return products.stream().map(p -> ProductDto.of(p)).collect(Collectors.toList());
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            port.deleteProduct(id);
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
