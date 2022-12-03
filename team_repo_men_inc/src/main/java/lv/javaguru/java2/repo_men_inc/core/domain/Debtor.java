package lv.javaguru.java2.repo_men_inc.core.domain;

import java.util.HashSet;
import java.util.Objects;

public class Debtor {
    private Long id;
    private String name;
    private HashSet<String> list = new HashSet<>();

    public Debtor() {
    }

    public Debtor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean addIem (String item) {
        return this.list.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debtor debtor = (Debtor) o;
        return id.equals(debtor.id) && name.equals(debtor.name) && list.equals(debtor.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, list);
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
