package org.taljaard.training.trnmicroservbeerservice.services;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.taljaard.training.trnmicroservbeerservice.domain.Beer;
import org.taljaard.training.trnmicroservbeerservice.repositories.BeerRepository;
import org.taljaard.training.trnmicroservbeerservice.web.controller.NotFoundException;
import org.taljaard.training.trnmicroservbeerservice.web.mappers.BeerMapper;
import org.taljaard.training.trnmicroservbeerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImp implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto createNewBeer(BeerDto newBeer) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(newBeer)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, @Valid BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.updater(beerDto);
        return beerMapper.beerToBeerDto(beerRepository.save(beer));

    }

}
