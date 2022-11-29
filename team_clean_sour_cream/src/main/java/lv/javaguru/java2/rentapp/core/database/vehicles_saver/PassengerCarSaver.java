package lv.javaguru.java2.rentapp.core.database.vehicles_saver;

import lv.javaguru.java2.rentapp.domain.Vehicle;

public class PassengerCarSaver extends VehicleSaver{
    @Override
    public Long save(Vehicle vehicle) {
        return null;
    }
}
//+ "INSERT INTO passenger_cars (`vehicle_id`, `passenger_amount`, `baggageAmount`, `doorsAmount`, `air_conditioning`) VALUES (?, ?, ?, ?, ?)",
//+ "INSERT INTO passenger_cars (`vehicle_id`, `passenger_amount`, `baggageAmount`, `doorsAmount`, `air_conditioning`)"
//                        + "VALUES (?, ?, ?, ?, ?);"