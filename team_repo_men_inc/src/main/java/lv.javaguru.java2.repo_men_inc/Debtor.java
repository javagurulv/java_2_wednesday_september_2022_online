package lv.javaguru.java2.repo_men_inc;

import java.util.HashSet;
import java.util.Objects;

public class Debtor {
    private String name;
    private HashSet<String> list = new HashSet<>();

    public Debtor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<String> getList() {
        return list;
    }

    public void setList(HashSet<String> list) {
        this.list = list;
    }

    public void addIem (String item) {
        this.list.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debtor debtor = (Debtor) o;
        return name.equals(debtor.name) && list.equals(debtor.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, list);
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
