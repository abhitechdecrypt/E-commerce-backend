package com.ecommerce.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductEcommerceService;

@SpringBootTest
public class ProductEcommControllerTest {

	private MockMvc mockMvc;

    @Mock
    private ProductEcommerceService productEcommerceService;

    @InjectMocks
    private ProductEcommController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetByCategory_ProductFound() throws Exception {
        Product product = new Product();
        product.setCategory("moblie");

        when(productEcommerceService.findProductByCategory(anyString())).thenReturn(product);

        mockMvc.perform(get("/findByCategory")
                .param("category", "mobile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
//                .andExpect(jsonPath("$.code").value("302"))
//                .andExpect(jsonPath("$.message").value("Product Found"))
//                .andExpect(jsonPath("$.data.category").value("Electronics"));
    }

    @Test
    public void testGetByCategory_ProductNotFound() throws Exception {
        when(productEcommerceService.findProductByCategory(anyString())).thenReturn(null);

        mockMvc.perform(get("/findByCategory")
                .param("category", "Unknown")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
//                .andExpect(jsonPath("$.code").value("404"))
//                .andExpect(jsonPath("$.message").value("Product Not Found"))
//                .andExpect(jsonPath("$.data").doesNotExist());
    }
}