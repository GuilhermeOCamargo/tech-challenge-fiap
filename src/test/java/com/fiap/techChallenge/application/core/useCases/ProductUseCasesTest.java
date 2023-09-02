package com.fiap.techChallenge.application.core.useCases;

import com.fiap.techChallenge.application.services.ProductService;
import com.fiap.techChallenge.application.useCases.ProductUseCases;
import com.fiap.techChallenge.domain.Product;
import com.fiap.techChallenge.presentation.dtos.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductUseCasesTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductUseCases productUseCases;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        Product product = createSampleProduct();

        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        ProductDto savedProduct = productUseCases.saveProduct(ProductDto.of(product));

        assertEquals(savedProduct.getId(), product.id());
        assertEquals(savedProduct.getCategory(), product.category());
        assertEquals(savedProduct.getPrice(), product.price());
        assertEquals(savedProduct.getDescription(), product.description());
        assertEquals(savedProduct.getImages(), product.images());

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct() {
        Product product = createSampleProduct();

        when(productService.getProductById(any())).thenReturn(product);
        when(productService.updateProduct(any(Product.class))).thenReturn(product);

        ProductDto savedProduct = productUseCases.updateProduct(ProductDto.of(product));

        assertEquals(savedProduct.getId(), product.id());
        assertEquals(savedProduct.getCategory(), product.category());
        assertEquals(savedProduct.getPrice(), product.price());
        assertEquals(savedProduct.getDescription(), product.description());
        assertEquals(savedProduct.getImages(), product.images());

        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        Product product = createSampleProduct();

        when(productService.getProductById(productId)).thenReturn(product);

        ProductDto savedProduct = productUseCases.getProductById(productId);

        assertNotNull(product);
        assertEquals(savedProduct.getId(), product.id());
        assertEquals(savedProduct.getCategory(), product.category());
        assertEquals(savedProduct.getPrice(), product.price());
        assertEquals(savedProduct.getDescription(), product.description());
        assertEquals(savedProduct.getImages(), product.images());

        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productsList = Arrays.asList(
                createSampleProduct(),
                createSampleProduct(),
                createSampleProduct()
        );

        when(productService.getAllProducts(any())).thenReturn(productsList);

        List<ProductDto> products = productUseCases.getAllProducts(null);

        assertEquals(productsList.size(), products.size());
        verify(productService, times(1)).getAllProducts(null);
    }

    @Test
    void testGetAllProductsByCategory() {
        String category = "Lanches";
        List<Product> productsList = Arrays.asList(
                createSampleProduct(),
                createSampleProduct(),
                createSampleProduct()
        );

        when(productService.getAllProducts(category)).thenReturn(productsList);

        List<ProductDto> products = productUseCases.getAllProducts(category);

        assertEquals(productsList.size(), products.size());

        verify(productService, times(1)).getAllProducts(category);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        Product product = createSampleProduct();

        when(productService.getProductById(productId)).thenReturn(product);

        productUseCases.deleteProduct(productId);

        verify(productService, times(1)).deleteProduct(productId);
    }

    private Product createSampleProduct() {
        return Product.builder()
                .id(1L)
                .category("Lanches")
                .price(50.00)
                .description("Sabor 4 queijos")
                .images(Arrays.asList("image1.jpg", "image2.jpg", "image3.jpg"))
                .build();
    }
}

