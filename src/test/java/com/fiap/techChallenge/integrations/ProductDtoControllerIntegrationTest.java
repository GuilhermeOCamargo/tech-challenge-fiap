package com.fiap.techChallenge.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techChallenge.application.useCases.ProductUseCases;
import com.fiap.techChallenge.presentation.controllers.ProductController;
import com.fiap.techChallenge.presentation.dtos.ProductDto;
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
class ProductDtoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductUseCases productUseCases;

    private ProductDto productDto;
    private List<ProductDto> productDtoList;

    @BeforeEach
    void setUp() {
        productDto = ProductDto.builder()
                .id(1L)
                .category("Lanches")
                .price(50.00)
                .description("Sabor 4 queijos")
                .images(Arrays.asList("image1.jpg", "image2.jpg", "image3.jpg"))
                .build();

        productDtoList = Arrays.asList(productDto);
    }

    @Test
    void createProduct() throws Exception {
        when(productUseCases.saveProduct(any(ProductDto.class))).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(productDto.getId()))
                .andExpect(jsonPath("$.category").value(productDto.getCategory()))
                .andExpect(jsonPath("$.price").value(productDto.getPrice()))
                .andExpect(jsonPath("$.description").value(productDto.getDescription()));

        verify(productUseCases, times(1)).saveProduct(any(ProductDto.class));
    }

    @Test
    void updateProduct() throws Exception {
        when(productUseCases.updateProduct(any(ProductDto.class))).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productDto.getId()))
                .andExpect(jsonPath("$.category").value(productDto.getCategory()))
                .andExpect(jsonPath("$.price").value(productDto.getPrice()))
                .andExpect(jsonPath("$.description").value(productDto.getDescription()));

        verify(productUseCases, times(1)).updateProduct(any(ProductDto.class));
    }

    @Test
    void getProductById() throws Exception {
        when(productUseCases.getProductById(productDto.getId())).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", productDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productDto.getId()))
                .andExpect(jsonPath("$.category").value(productDto.getCategory()))
                .andExpect(jsonPath("$.price").value(productDto.getPrice()))
                .andExpect(jsonPath("$.description").value(productDto.getDescription()));

        verify(productUseCases, times(1)).getProductById(productDto.getId());
    }

    @Test
    void getAllProducts_withCategory() throws Exception {
        String category = "Lanches";
        when(productUseCases.getAllProducts(category)).thenReturn(productDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("category", category))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(productDto.getId()))
                .andExpect(jsonPath("$[0].category").value(productDto.getCategory()))
                .andExpect(jsonPath("$[0].price").value(productDto.getPrice()))
                .andExpect(jsonPath("$[0].description").value(productDto.getDescription()));

        verify(productUseCases, times(1)).getAllProducts(category);
    }

    @Test
    void getAllProducts_withoutCategory() throws Exception {
        when(productUseCases.getAllProducts(null)).thenReturn(productDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(productDto.getId()))
                .andExpect(jsonPath("$[0].category").value(productDto.getCategory()))
                .andExpect(jsonPath("$[0].price").value(productDto.getPrice()))
                .andExpect(jsonPath("$[0].description").value(productDto.getDescription()));

        verify(productUseCases, times(1)).getAllProducts(null);
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", productDto.getId()))
                .andExpect(status().isNoContent());

        verify(productUseCases, times(1)).deleteProduct(productDto.getId());
    }
}

