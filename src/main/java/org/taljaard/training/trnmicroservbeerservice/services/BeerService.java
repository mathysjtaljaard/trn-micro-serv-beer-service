package org.taljaard.training.trnmicroservbeerservice.services;

import java.util.UUID;

import javax.validation.Valid;

import org.taljaard.training.trnmicroservbeerservice.web.model.BeerDto;

public interface BeerService {

    BeerDto getById(UUID beerId);

    BeerDto createNewBeer(BeerDto newBeer);

    BeerDto updateBeer(UUID beerId, @Valid BeerDto beertoUpdate);

}
