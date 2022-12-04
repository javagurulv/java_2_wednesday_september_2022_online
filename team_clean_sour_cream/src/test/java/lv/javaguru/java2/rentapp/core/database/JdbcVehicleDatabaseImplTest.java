package lv.javaguru.java2.rentapp.core.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcVehicleDatabaseImplTest {

    private JdbcVehicleDatabaseImpl jdbcVehicleDatabase;



    @Test
    void getById() {
        jdbcVehicleDatabase = new JdbcVehicleDatabaseImpl();
        jdbcVehicleDatabase.getById(1L);
    }
}