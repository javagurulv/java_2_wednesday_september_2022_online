package lv.javaguru.java2.rentapp.enums;

public enum Colour {

    BLACK("Black"),

    WHITE("White"),

    ORANGE("Orange"),

    YELLOW("Yellow"),

    RED("Red"),

    BLUE("Blue"),

    GREEN("Green");

    private String nameColour;

    Colour(String nameColourString) {
        this.nameColour = nameColourString;
    }

    public String getNameColour() {
        return nameColour;
    }
}