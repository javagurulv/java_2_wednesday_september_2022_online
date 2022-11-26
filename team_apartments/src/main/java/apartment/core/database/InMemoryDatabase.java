package apartment.core.database;

import apartment.Apartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryDatabase implements Database{

    private Long nextId = 1L;

    private List<Apartment> apartments = new ArrayList<>();

    @Override
    public void save(Apartment apartment) {
        apartment.setId(nextId);
        nextId++;
        apartments.add(apartment);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isApartmentDeleted = false;
        Optional<Apartment> apartmentToDeleteOpt = apartments.stream()
                        .filter(apartment -> apartment.getId().equals(id))
                        .findFirst();
        if (apartmentToDeleteOpt.isPresent()){
            Apartment apartmentToRemove = apartmentToDeleteOpt.get();
            isApartmentDeleted = apartments.remove(apartmentToRemove);
        }
        return isApartmentDeleted;
    }

    @Override
    public List<Apartment> getAllApartment() {
        return apartments;
    }

    @Override
    public List<Apartment> findByAddress (String address){
        return apartments.stream()
                .filter(apartment -> apartment.getAddress().equals(address))
                .collect(Collectors.toList());
    }
    @Override
    public List<Apartment> findByRoomAmount(Long roomAmount){
        return apartments.stream()
                .filter(apartment -> apartment.getRoomsAmount().equals(roomAmount))
                .collect(Collectors.toList());
    }

    @Override
    public  List<Apartment> findByFloor(Long floor){
        return apartments.stream()
                .filter(apartment -> apartment.getFloor().equals(floor))
                .collect(Collectors.toList());
    }

}
