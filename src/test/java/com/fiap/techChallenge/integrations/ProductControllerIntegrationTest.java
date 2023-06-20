package com.fiap.techChallenge.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techChallenge.application.product.ProductService;
import com.fiap.techChallenge.domain.Product;
import com.fiap.techChallenge.interfaces.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private Product product;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        product =  Product.builder()
                .id(1L)
                .category("Lanches")
                .price(50.00)
                .description("Sabor 4 queijos")
                .images(Arrays.asList("image1.jpg", "image2.jpg", "image3.jpg"))
                .build();

        productList = Arrays.asList(product);
    }

    @Test
    void createProduct() throws Exception {
        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.category").value(product.getCategory()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.description").value(product.getDescription()));

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void updateProduct() throws Exception {
        when(productService.updateProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.category").value(product.getCategory()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.description").value(product.getDescription()));

        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    void getProductById() throws Exception {
        when(productService.getProductById(product.getId())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.category").value(product.getCategory()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.description").value(product.getDescription()));

        verify(productService, times(1)).getProductById(product.getId());
    }

    @Test
    void getAllProducts_withCategory() throws Exception {
        String category = "Lanches";
        when(productService.getAllProducts(category)).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("category", category))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(product.getId()))
                .andExpect(jsonPath("$[0].category").value(product.getCategory()))
                .andExpect(jsonPath("$[0].price").value(product.getPrice()))
                .andExpect(jsonPath("$[0].description").value(product.getDescription()));

        verify(productService, times(1)).getAllProducts(category);
    }

    @Test
    void getAllProducts_withoutCategory() throws Exception {
        when(productService.getAllProducts(null)).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(product.getId()))
                .andExpect(jsonPath("$[0].category").value(product.getCategory()))
                .andExpect(jsonPath("$[0].price").value(product.getPrice()))
                .andExpect(jsonPath("$[0].description").value(product.getDescription()));

        verify(productService, times(1)).getAllProducts(null);
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", product.getId()))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(product.getId());
    }
}

