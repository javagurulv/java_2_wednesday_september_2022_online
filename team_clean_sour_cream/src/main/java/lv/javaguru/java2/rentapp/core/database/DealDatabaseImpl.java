package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.domain.RentDeal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Deprecated()
//@Component
public class DealDatabaseImpl implements DealDatabase {

    private Long nextId = 1L;
    private List<RentDeal> rentDealsDatabase = new ArrayList<>();

    @Override
    public Long save(RentDeal rentDeal) {
        rentDeal.setId(nextId);
        nextId++;
        rentDealsDatabase.add(rentDeal);
        return rentDeal.getId();
    }

    @Override
    public List<RentDeal> getAllDeals() {
        return new ArrayList<>(rentDealsDatabase);
    }

    @Override
    public Optional<RentDeal> getDealById(Long id) {
        return null;
    }
}
