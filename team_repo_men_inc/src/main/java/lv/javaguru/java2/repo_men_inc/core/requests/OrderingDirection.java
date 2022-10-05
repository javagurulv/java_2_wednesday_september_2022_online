package lv.javaguru.java2.repo_men_inc.core.requests;

public enum OrderingDirection {
    ASC("ascending"),
    DESC("descending"),
    INVALID("invalid"),
    EMPTY("empty");

    private final String direction;

    OrderingDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
