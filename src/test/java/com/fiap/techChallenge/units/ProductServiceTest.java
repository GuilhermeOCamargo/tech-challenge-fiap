package com.fiap.techChallenge.units;

import com.fiap.techChallenge.application.product.ProductServiceImpl;
import com.fiap.techChallenge.domain.Product;
import com.fiap.techChallenge.infrastructure.product.ProductEntity;
import com.fiap.techChallenge.infrastructure.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testSaveProduct() {
        Product product = createSampleProduct();
        ProductEntity savedProductEntity = createSampleProductEntity();

        when(productRepository.save(any(ProductEntity.class))).thenReturn(savedProductEntity);

        Product savedProduct = productService.saveProduct(product);

        assertEquals(savedProduct.getId(), savedProductEntity.getId());
        assertEquals(savedProduct.getCategory(), savedProductEntity.getCategory());
        assertEquals(savedProduct.getPrice(), savedProductEntity.getPrice());
        assertEquals(savedProduct.getDescription(), savedProductEntity.getDescription());
        assertEquals(savedProduct.getImages(), savedProductEntity.getImages());

        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testUpdateProduct() {
        Product product = createSampleProduct();
        ProductEntity existingProductEntity = createSampleProductEntity();

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(existingProductEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(existingProductEntity);

        Product updatedProduct = productService.updateProduct(product);

        assertEquals(updatedProduct.getId(), existingProductEntity.getId());
        assertEquals(updatedProduct.getCategory(), existingProductEntity.getCategory());
        assertEquals(updatedProduct.getPrice(), existingProductEntity.getPrice());
        assertEquals(updatedProduct.getDescription(), existingProductEntity.getDescription());
        assertEquals(updatedProduct.getImages(), existingProductEntity.getImages());

        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        ProductEntity existingProductEntity = createSampleProductEntity();

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProductEntity));

        Product product = productService.getProductById(productId);

        assertNotNull(product);
        assertEquals(existingProductEntity.getId(), product.getId());
        assertEquals(existingProductEntity.getCategory(), product.getCategory());
        assertEquals(existingProductEntity.getPrice(), product.getPrice());
        assertEquals(existingProductEntity.getDescription(), product.getDescription());
        assertEquals(existingProductEntity.getImages(), product.getImages());

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetAllProducts() {
        List<ProductEntity> productEntityList = Arrays.asList(
                createSampleProductEntity(),
                createSampleProductEntity(),
                createSampleProductEntity()
        );

        when(productRepository.findAll()).thenReturn(productEntityList);

        List<Product> products = productService.getAllProducts(null);

        assertEquals(productEntityList.size(), products.size());
        for (int i = 0; i < productEntityList.size(); i++) {
            ProductEntity productEntity = productEntityList.get(i);
            Product product = products.get(i);
            assertEquals(productEntity.getId(), product.getId());
            assertEquals(productEntity.getCategory(), product.getCategory());
            assertEquals(productEntity.getPrice(), product.getPrice());
            assertEquals(productEntity.getDescription(), product.getDescription());
            assertEquals(productEntity.getImages(), product.getImages());
        }

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetAllProductsByCategory() {
        String category = "Lanches";
        List<ProductEntity> productListByCategory = Arrays.asList(
                createSampleProductEntity(),
                createSampleProductEntity(),
                createSampleProductEntity()
        );

        when(productRepository.findByCategory(category)).thenReturn(productListByCategory);

        List<Product> products = productService.getAllProducts(category);

        assertEquals(productListByCategory.size(), products.size());
        for (int i = 0; i < productListByCategory.size(); i++) {
            ProductEntity productEntity = productListByCategory.get(i);
            Product product = products.get(i);
            assertEquals(productEntity.getId(), product.getId());
            assertEquals(productEntity.getCategory(), product.getCategory());
            assertEquals(productEntity.getPrice(), product.getPrice());
            assertEquals(productEntity.getDescription(), product.getDescription());
            assertEquals(productEntity.getImages(), product.getImages());
        }

        verify(productRepository, times(1)).findByCategory(category);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        ProductEntity existingProductEntity = createSampleProductEntity();

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProductEntity));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).delete(existingProductEntity);
    }

    private Product createSampleProduct() {
        return Product.builder()
                .category("Lanches")
                .price(50.00)
                .description("Sabor 4 queijos")
                .images(Arrays.asList("image1.jpg", "image2.jpg", "image3.jpg"))
                .build();
    }

    private ProductEntity createSampleProductEntity() {
        return ProductEntity.builder()
                .id(1L)
                .category("Sobremesa")
                .price(10.99)
                .description("Torta de limao")
                .images(Arrays.asList("image1.jpg", "image2.jpg", "image3.jpg"))
                .build();
    }
}

