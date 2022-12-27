package lv.javaguru.java2.repo_men_inc.core.requests;

public enum OrderingType {
    NAME("name"),
    INVALID("invalid"),
    EMPTY("empty");

    private final String type;

    OrderingType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
