package lv.javaguru.java2.cookingApp.core.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "recipes_to_ingredients")
public class RecipeIngredient {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "measurement")
    private String measurement;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, Double amount, String measurement) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
        this.measurement = measurement;
    }
}
