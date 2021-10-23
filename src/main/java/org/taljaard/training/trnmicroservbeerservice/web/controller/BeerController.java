package org.taljaard.training.trnmicroservbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taljaard.training.trnmicroservbeerservice.services.BeerService;
import org.taljaard.training.trnmicroservbeerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(BeerController.REQUEST_MAPPING_PATH)
public class BeerController {

    public static final String REQUEST_MAPPING_PATH = "/api/v1/beer";

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<BeerDto>(beerService.getById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> createNewBeer(@Valid @RequestBody BeerDto newBeer) {
        return new ResponseEntity<>(beerService.createNewBeer(newBeer), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeerById(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beertoUpdate) {
        return new ResponseEntity<>(beerService.updateBeer(beerId, beertoUpdate), HttpStatus.NO_CONTENT);
    }
}
