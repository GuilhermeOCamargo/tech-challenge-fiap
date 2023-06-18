package com.fiap.techChallenge.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techChallenge.application.product.ProductService;
import com.fiap.techChallenge.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    public void testCreateProduct() throws Exception {
        Product product = createSampleProduct();

        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(product.getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(product.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.images").isArray());

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = createSampleProduct();

        when(productService.updateProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(product.getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(product.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.images").isArray());

        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    public void testGetProductById() throws Exception {
        Long productId = 1L;
        Product product = createSampleProduct();
        product.setId(productId);

        when(productService.getProductById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(product.getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(product.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.images").isArray());

        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(createSampleProduct(), createSampleProduct(), createSampleProduct());

        when(productService.getAllProducts(null)).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(products.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value(products.get(0).getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(products.get(0).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value(products.get(0).getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].images").isArray());

        verify(productService, times(1)).getAllProducts(null);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Long productId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk());

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
