package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.domain.RentDeal;

import java.util.List;
import java.util.Optional;

public interface DealDatabase {

    Long save(RentDeal rentDeal);

    List<RentDeal> getAllDeals();

    Optional<RentDeal> getDealById(Long id);

}
