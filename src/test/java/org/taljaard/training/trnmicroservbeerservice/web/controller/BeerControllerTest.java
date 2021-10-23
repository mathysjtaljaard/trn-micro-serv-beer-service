package org.taljaard.training.trnmicroservbeerservice.web.controller;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.taljaard.training.trnmicroservbeerservice.web.model.BeerDto;
import org.taljaard.training.trnmicroservbeerservice.web.model.BeerStyleEnum;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getBeerById() throws Exception {
        UUID beerId = createBeer();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/beer/" + beerId.toString()).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createNewBeer() throws Exception {
        createBeer();
    }

    @Test
    public void updateBeerById() throws Exception {

        UUID beerId = createBeer();
        BeerDto beerDto = getBeer();
        beerDto.setBeerName("Funky Monkey");
        String beerJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + beerId.toString())
                .contentType(MediaType.APPLICATION_JSON).content(beerJson)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private UUID createBeer() throws Exception {

        BeerDto beerDto = getBeer();
        String beerJson = objectMapper.writeValueAsString(beerDto);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/beer/").content(beerJson).contentType(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
        String responseBody = result.andReturn().getResponse().getContentAsString();
        BeerDto beer = objectMapper.readValue(responseBody, BeerDto.class);
        return beer.getId();
    }

    private BeerDto getBeer() {
        return BeerDto.builder().beerName("Funny Cat").beerStyles(BeerStyleEnum.ALE).upc(12345L)
                .price(new BigDecimal("12.22")).build();
    }
}
