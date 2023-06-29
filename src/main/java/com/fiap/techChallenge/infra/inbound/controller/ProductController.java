package com.fiap.techChallenge.infra.inbound.controller;

import com.fiap.techChallenge.infra.inbound.dto.ProductDto;
import com.fiap.techChallenge.infra.inbound.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProductDto = productService.saveProduct(productDto);
        return ResponseEntity.status(CREATED).body(createdProductDto);
    }

    @PutMapping
    @ResponseStatus(OK)
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) {
        ProductDto updatedProductDto = productService.updateProduct(productDto);
        return ResponseEntity.status(OK).body(updatedProductDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ResponseEntity<?> getProductById(@Valid @PathVariable Long id) {
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.status(OK).body(productDto);
    }

    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<?> getAllProducts(@Valid @RequestParam(value = "category", required = false) String category) {
        List<ProductDto> productDtos = productService.getAllProducts(category);
        return ResponseEntity.status(OK).body(productDtos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<?> deleteProduct(@Valid @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(NO_CONTENT).body(null);
    }
}

