package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lombok.EqualsAndHashCode;
import lv.javaguru.java2.rentapp.domain.Vehicle;

@EqualsAndHashCode
public class TransmissionTypeCriteria implements SearchCriteria {

    private String transmissionType;

    public TransmissionTypeCriteria(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public boolean test(Vehicle vehicle) {

        return vehicle.getTransmissionType().getNameTransmissionType().equalsIgnoreCase(transmissionType);

    }
}
