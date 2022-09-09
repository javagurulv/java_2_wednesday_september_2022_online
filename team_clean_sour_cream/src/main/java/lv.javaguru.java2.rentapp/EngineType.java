package lv.javaguru.java2.rentapp;

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
}
