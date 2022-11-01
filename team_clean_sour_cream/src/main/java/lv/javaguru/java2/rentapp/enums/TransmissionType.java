package lv.javaguru.java2.rentapp.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<String> getAllEnumValues() {
        return EnumSet.allOf(TransmissionType.class).stream()
                .map(TransmissionType::getNameTransmissionType)
                .collect(Collectors.toList());
    }

    public static List<String> getAllEnumValuesExceptNone() {
        return getAllEnumValues().stream()
                .filter(value -> !value.equalsIgnoreCase(TransmissionType.NONE.getNameTransmissionType()))
                .collect(Collectors.toList());
    }
}
