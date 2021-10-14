package org.taljaard.training.trnmicroservbeerservice.web.controller;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.taljaard.training.trnmicroservbeerservice.web.model.BeerDto;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getBeerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createNewBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder().build();
        String beerJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/beer/").content(beerJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void updateBeerById() throws Exception {

        BeerDto beerDto = BeerDto.builder().build();
        String beerJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON).content(beerJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
