package lv.javaguru.java2.rentapp.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum Colour {

    BLACK("Black"),

    WHITE("White"),

    ORANGE("Orange"),

    YELLOW("Yellow"),

    RED("Red"),

    BLUE("Blue"),

    GREEN("Green");

    private String nameColour;

    private static List<String> colourEnumsAllVariants;

    Colour(String nameColourString) {
        this.nameColour = nameColourString;
    }

    public String getNameColour() {
        return nameColour;
    }

    public List<String> getColourEnumsAllVariants() {
        return EnumSet.allOf(Colour.class).stream()
                .map(Colour::getNameColour)
                .collect(Collectors.toList());
    }
}