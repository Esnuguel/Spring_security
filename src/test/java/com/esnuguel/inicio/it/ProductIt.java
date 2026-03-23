package com.esnuguel.inicio.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;
import com.esnuguel.inicio.product.infrestructure.api.dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureRestTestClient
@Slf4j
public class ProductIt {

    @Autowired
    private RestTestClient restTestClient; // ← nuevo cliente en SB4

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductoRepository productRepository;

    @BeforeEach
    void setUp() {
        log.info("Setting up integration test");
        productRepository.uppsert(
                Product.builder().id(1L).name("Product 1").description("Description 1").price(100.0).build()
        );
    }

    @AfterEach
    void tearDown() {
        log.info("Tearing down integration test");
        productRepository.deleteById(1L);
    }

    @Test
    public void getProductByIdExits() {
        restTestClient.get().uri("/api/v1/products/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .value(body -> {
                    assertEquals("Product 1", body.getName());
                    assertEquals("Description 1", body.getDescription());
                    assertEquals(100.0, body.getPrice());
                });
    }

    @Test
    public void saveProduct() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "image.jpeg", "image/jpeg", "image".getBytes());

        mockMvc.perform(
                multipart(HttpMethod.POST, "/api/v1/products")
                        .file(file)
                        .param("id", "2")
                        .param("name", "Name 2")
                        .param("description", "Description 2")
                        .param("price", "150.00")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isCreated()).andExpect((jsonPath("$.id").value(2L)));
    }
}