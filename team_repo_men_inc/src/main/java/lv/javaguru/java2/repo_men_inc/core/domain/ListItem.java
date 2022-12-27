package lv.javaguru.java2.repo_men_inc.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "lists")
public class ListItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "debtor_id", nullable = false)
    private Debtor debtor;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item items;

    public ListItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Debtor getDebtor() {
        return debtor;
    }

    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }

    public Item getItems() {
        return items;
    }

    public void setItems(Item items) {
        this.items = items;
    }
}
