package org.taljaard.training.trnmicroservbeerservice.web.mappers;

import org.mapstruct.Mapper;
import org.taljaard.training.trnmicroservbeerservice.domain.Beer;
import org.taljaard.training.trnmicroservbeerservice.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
