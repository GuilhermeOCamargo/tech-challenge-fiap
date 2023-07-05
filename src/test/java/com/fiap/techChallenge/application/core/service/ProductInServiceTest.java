package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Product;
import com.fiap.techChallenge.application.ports.out.ProductOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductInServiceTest {
    private ProductInService productService;

    @Mock
    private ProductOutPort port;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductInService(port);
    }

    @Test
    void testSaveProduct() {
        Product product = createSampleProduct();

        when(port.saveProduct(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertEquals(savedProduct.id(), product.id());
        assertEquals(savedProduct.category(), product.category());
        assertEquals(savedProduct.price(), product.price());
        assertEquals(savedProduct.description(), product.description());
        assertEquals(savedProduct.images(), product.images());

        verify(port, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct() {
        Product product = createSampleProduct();

        when(port.getProductById(any())).thenReturn(product);
        when(port.updateProduct(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.updateProduct(product);

        assertEquals(savedProduct.id(), product.id());
        assertEquals(savedProduct.category(), product.category());
        assertEquals(savedProduct.price(), product.price());
        assertEquals(savedProduct.description(), product.description());
        assertEquals(savedProduct.images(), product.images());

        verify(port, times(1)).getProductById(any());
        verify(port, times(1)).updateProduct(any(Product.class));
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        Product product = createSampleProduct();

        when(port.getProductById(productId)).thenReturn(product);

        Product savedProduct = productService.getProductById(productId);

        assertNotNull(product);
        assertEquals(savedProduct.id(), product.id());
        assertEquals(savedProduct.category(), product.category());
        assertEquals(savedProduct.price(), product.price());
        assertEquals(savedProduct.description(), product.description());
        assertEquals(savedProduct.images(), product.images());

        verify(port, times(1)).getProductById(productId);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productsList = Arrays.asList(
                createSampleProduct(),
                createSampleProduct(),
                createSampleProduct()
        );

        when(port.getAllProducts(any())).thenReturn(productsList);

        List<Product> products = productService.getAllProducts(null);

        assertEquals(productsList.size(), products.size());
        verify(port, times(1)).getAllProducts(null);
    }

    @Test
    void testGetAllProductsByCategory() {
        String category = "Lanches";
        List<Product> productsList = Arrays.asList(
                createSampleProduct(),
                createSampleProduct(),
                createSampleProduct()
        );

        when(port.getAllProducts(category)).thenReturn(productsList);

        List<Product> products = productService.getAllProducts(category);

        assertEquals(productsList.size(), products.size());

        verify(port, times(1)).getAllProducts(category);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        Product product = createSampleProduct();

        when(port.getProductById(productId)).thenReturn(product);

        productService.deleteProduct(productId);

        verify(port, times(1)).getProductById(productId);
        verify(port, times(1)).deleteProduct(productId);
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

