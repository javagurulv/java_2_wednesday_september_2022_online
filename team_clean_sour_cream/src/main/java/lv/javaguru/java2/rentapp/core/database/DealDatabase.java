package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.domain.RentDeal;

import java.util.List;

public interface DealDatabase {

    public Long save(RentDeal rentDeal);
    public List<RentDeal> getAllDeals();
    public RentDeal getDealById(Long id);


}
