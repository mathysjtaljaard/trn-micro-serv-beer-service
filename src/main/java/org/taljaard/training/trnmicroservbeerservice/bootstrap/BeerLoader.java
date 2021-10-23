package org.taljaard.training.trnmicroservbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.taljaard.training.trnmicroservbeerservice.domain.Beer;
import org.taljaard.training.trnmicroservbeerservice.repositories.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234200019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository repo;

    public BeerLoader(BeerRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (repo.count() == 0) {
            repo.save(Beer.builder().beerName("Wacky Toes").beerStyle("Gross").upc(BEER_1_UPC).quantityToBrew(200)
                    .minOnHand(12).price(BigDecimal.valueOf(12.95)).build());

            repo.save(Beer.builder().beerName("Nasty brown").beerStyle("Oops").upc(BEER_2_UPC).quantityToBrew(200)
                    .minOnHand(10).price(BigDecimal.valueOf(10.95)).build());

            repo.save(Beer.builder().beerName("Crack Monkey").beerStyle("Oops").upc(BEER_3_UPC).quantityToBrew(200)
                    .minOnHand(10).price(BigDecimal.valueOf(10.95)).build());
        }
    }
}
