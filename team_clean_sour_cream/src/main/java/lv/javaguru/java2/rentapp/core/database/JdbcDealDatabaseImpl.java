package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.domain.RentDeal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcDealDatabaseImpl implements DealDatabase{

    @Override
    public Long save(RentDeal rentDeal) {
        return null;
    }

    @Override
    public List<RentDeal> getAllDeals() {
        return null;
    }

    @Override
    public RentDeal getDealById(Long id) {
        return null;
    }
}
