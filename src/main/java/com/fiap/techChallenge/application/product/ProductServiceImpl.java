package com.fiap.techChallenge.application.product;

import com.fiap.techChallenge.domain.Product;
import com.fiap.techChallenge.infrastructure.exceptions.NotFoundException;
import com.fiap.techChallenge.infrastructure.product.ProductEntity;
import com.fiap.techChallenge.infrastructure.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private final String PRODUCT_NOT_FOUND = "Product not found";

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = ProductEntity.createEntity(product);
        return fromModel(productRepository.save(productEntity));
    }

    @Override
    public Product updateProduct(Product product) {
        ProductEntity productEntity = productRepository.findById(product.getId()).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));

        productEntity.setCategory(product.getCategory());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        productEntity.setImages(product.getImages());

        return fromModel(productRepository.save(productEntity));
    }

    @Override
    public Product getProductById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        return fromModel(product);
    }

    @Override
    public List<Product> getAllProducts(String category) {

        if(category != null) {
            return getProductsByCategory(category);
        }

        List<ProductEntity> productEntityList = productRepository.findAll();

        List<Product> products = productEntityList.stream()
                .map(p -> fromModel(p))
                .collect(Collectors.toList());

        return products;
    }

    private List<Product> getProductsByCategory(String category) {
        List<ProductEntity> productListByCategory = productRepository.findByCategory(category);

        List<Product> products = productListByCategory.stream()
                .map(p -> fromModel(p))
                .collect(Collectors.toList());

        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        productRepository.delete(productEntity);
    }

    private Product fromModel(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .category(productEntity.getCategory())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .images(productEntity.getImages())
                .build();
    }
}
