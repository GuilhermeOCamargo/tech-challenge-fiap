package com.fiap.techChallenge.infra.outbound.adapters;

import com.fiap.techChallenge.application.core.domain.Product;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.out.ProductOutPort;
import com.fiap.techChallenge.infra.outbound.repository.mariadb.ProductRepository;
import com.fiap.techChallenge.infra.outbound.repository.mariadb.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fiap.techChallenge.application.core.util.ConstantsUtil.PRODUCT_NOT_FOUND;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductAdapter implements ProductOutPort {

    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(new ProductEntity(product)).toDomain();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(new ProductEntity(product)).toDomain();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        return productEntity.isPresent() ? productEntity.get().toDomain() : null;
    }

    @Override
    public List<Product> getAllProducts(String category) {

        if (category != null) {
            return getProductsByCategory(category);
        }

        List<ProductEntity> productEntityList = productRepository.findAll();

        List<Product> products = productEntityList.stream()
                .map(p -> p.toDomain())
                .collect(Collectors.toList());

        return products;
    }

    private List<Product> getProductsByCategory(String category) {
        List<ProductEntity> productListByCategory = productRepository.findByCategory(category);

        List<Product> products = productListByCategory.stream()
                .map(p -> p.toDomain())
                .collect(Collectors.toList());

        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        productRepository.delete(productEntity);
    }
}
