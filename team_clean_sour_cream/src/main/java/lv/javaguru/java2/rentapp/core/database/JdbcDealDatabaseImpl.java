package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.database.row_mappers.RentDealRowMapper;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcDealDatabaseImpl implements DealDatabase{

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private RentDealRowMapper rentDealRowMapper;

    @Override
    public Long save(RentDeal rentDeal) {
        SimpleJdbcInsert insertRentDeal = new SimpleJdbcInsert(jdbcTemplate).withTableName("rent_deals").usingGeneratedKeyColumns("id");
        Map<String, Object> rentDealArgs = new HashMap<>();
        rentDealArgs.put("client_id", rentDeal.getClient().getId());
        rentDealArgs.put("vehicle_id", rentDeal.getVehicle().getId());
        rentDealArgs.put("start_date", rentDeal.getStartDate());
        rentDealArgs.put("end_date", rentDeal.getEndDate());
        rentDealArgs.put("duration", rentDeal.getRentDuration());
        rentDealArgs.put("cost", rentDeal.getRentCost());
        return insertRentDeal.executeAndReturnKey(rentDealArgs).longValue();
    }

    @Override
    public List<RentDeal> getAllDeals() {
        String sql = "SELECT * FROM rent_deals";
        return jdbcTemplate.query(sql, rentDealRowMapper);
    }

    @Override
    public Optional<RentDeal> getDealById(Long id) {
        String sql = "SELECT * FROM rent_deals WHERE id = ?";
        List<RentDeal> rentDeals = jdbcTemplate.query(sql, rentDealRowMapper, id);
        return rentDeals.stream().findFirst();
    }
}
