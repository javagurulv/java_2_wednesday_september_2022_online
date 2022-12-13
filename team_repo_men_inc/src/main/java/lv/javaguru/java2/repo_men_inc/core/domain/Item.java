package lv.javaguru.java2.repo_men_inc.core.domain;

public class Item {
    private long id;
    private String name;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
