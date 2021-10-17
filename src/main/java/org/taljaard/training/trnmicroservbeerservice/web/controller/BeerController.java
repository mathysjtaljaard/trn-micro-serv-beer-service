package org.taljaard.training.trnmicroservbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taljaard.training.trnmicroservbeerservice.web.model.BeerDto;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(BeerController.REQUEST_MAPPING_PATH)
public class BeerController {

    public static final String REQUEST_MAPPING_PATH = "/api/v1/beer";

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createNewBeer(@Valid @RequestBody BeerDto newBeer) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, String.format("%s/%s", REQUEST_MAPPING_PATH, UUID.randomUUID()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beertoUpdate) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
