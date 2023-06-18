package com.fiap.techChallenge.interfaces;

import com.fiap.techChallenge.application.product.ProductService;
import com.fiap.techChallenge.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(value = "category", required = false) String category) {
        return productService.getAllProducts(category);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

