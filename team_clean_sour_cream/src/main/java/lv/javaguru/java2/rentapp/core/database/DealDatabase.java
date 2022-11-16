package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.domain.RentDeal;

import java.util.List;

public interface DealDatabase {

    Long save(RentDeal rentDeal);

    List<RentDeal> getAllDeals();

    RentDeal getDealById(Long id);

}
