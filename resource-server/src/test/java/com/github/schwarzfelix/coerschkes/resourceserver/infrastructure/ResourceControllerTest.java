package com.github.schwarzfelix.coerschkes.resourceserver.infrastructure;

import com.github.schwarzfelix.coerschkes.resourceserver.domain.application.CampingTentRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CampingTentRepository repository;

    @Test
    void tents_should_return_200() throws Exception {
        mvc.perform(get("/tents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void tent_should_return_200() throws Exception {
        mvc.perform(get("/tents/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(1)
    void updateTent_should_return_201() throws Exception {
        mvc.perform(put("/tents/1"))
                .andExpect(status().isCreated());
    }

    @Test
    void tent_should_return_404_if_tent_not_found() throws Exception {
        mvc.perform(get("/tents/399"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(2)
    void updateTent_should_return_400_if_stock_is_empty() throws Exception {
        while (repository.findById(2L).orElseThrow().stock() != 0) {
            mvc.perform(put("/tents/2"));
        }
        mvc.perform(put("/tents/2"))
                .andExpect(status().isBadRequest());
    }
}