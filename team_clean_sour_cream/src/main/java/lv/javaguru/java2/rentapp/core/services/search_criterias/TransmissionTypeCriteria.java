package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

public class TransmissionTypeCriteria implements SearchCriteria {

    private TransmissionType transmissionType;

    public TransmissionTypeCriteria(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public boolean test(Vehicle vehicle) {

        return vehicle.getTransmissionType().equals(transmissionType);

    }
}
