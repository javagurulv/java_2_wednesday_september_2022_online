package lv.javaguru.java2.rentapp.enums;

public enum TransmissionType {

    MANUAL("Manual"),

    AUTOMATIC("Automatic"),

    NONE("None");

    private String nameTransmissionType;

    TransmissionType(String nameTransmissionTypeString) {
        this.nameTransmissionType = nameTransmissionTypeString;
    }

    public String getNameTransmissionType() {
        return nameTransmissionType;
    }
}
