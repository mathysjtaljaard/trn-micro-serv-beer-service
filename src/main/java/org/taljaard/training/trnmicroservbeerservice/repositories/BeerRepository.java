package org.taljaard.training.trnmicroservbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.taljaard.training.trnmicroservbeerservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}