package com.fiap.techChallenge.application.product;

import com.fiap.techChallenge.domain.Product;
import com.fiap.techChallenge.infrastructure.product.ProductEntity;
import com.fiap.techChallenge.infrastructure.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = ProductEntity.builder()
                .price(product.getPrice())
                .category(product.getCategory())
                .description(product.getDescription())
                .images(product.getImages())
                .build();

        return fromModel(productRepository.save(productEntity));
    }

    @Override
    public Product updateProduct(Product product) {
        ProductEntity productEntity = productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product not found"));

        productEntity.setCategory(product.getCategory());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        productEntity.setImages(product.getImages());

        return fromModel(productRepository.save(productEntity));
    }

    @Override
    public Product getProductById(Long id) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);

        if (productOpt.isPresent()) {
            ProductEntity productEntity = productOpt.get();
            return fromModel(productEntity);
        }
        return null;
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
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
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
