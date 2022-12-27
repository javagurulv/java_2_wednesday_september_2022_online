package lv.javaguru.java2.repo_men_inc.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "debtors")
public class Debtor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debtor debtor = (Debtor) o;
        return id.equals(debtor.id) && name.equals(debtor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

