package lv.javaguru.java2.rentapp.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum EngineType {

    PETROL("Petrol"),

    DIESEL("Diesel"),

    GAS("Gas"),

    ELECTRIC("Electric"),

    HYBRID("Hybrid"),

    NONE("None");

    private String nameEngineType;

    EngineType(String nameEngineTypeString) {
        this.nameEngineType = nameEngineTypeString;
    }

    public String getNameEngineType() {
        return nameEngineType;
    }

    public static List<String> getAllEnumValues() {
        return EnumSet.allOf(EngineType.class).stream()
                .map(EngineType::getNameEngineType)
                .collect(Collectors.toList());
    }

    public static List<String> getAllEnumValuesExceptNone() {
        return getAllEnumValues().stream()
                .filter(value -> !value.equalsIgnoreCase(EngineType.NONE.getNameEngineType()))
                .collect(Collectors.toList());
    }
}
