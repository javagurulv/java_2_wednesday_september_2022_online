package apartment.core.database;

import apartment.Apartment;

import java.util.List;

public interface Database {

     void save (Apartment apartment);

     boolean deleteById(Long id);

     List<Apartment> getAllApartment();

     List<Apartment> findByAddress(String address);

     List<Apartment> findByRoomAmount(Long roomAmount);

     List<Apartment> findByFloor(Long floor);


}
