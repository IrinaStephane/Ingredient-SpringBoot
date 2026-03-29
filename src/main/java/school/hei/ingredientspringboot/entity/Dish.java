package school.hei.ingredientspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un plat.
 * Migré depuis la classe JDBC Dish.
 */
@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    // Note : si PostgreSQL lève une erreur de cast, ajouter :
    // @Column(columnDefinition = "dish_type")
    @Enumerated(EnumType.STRING)
    @Column(name = "dish_type")
    private DishTypeEnum dishType;

    @Column(name = "selling_price")
    private Double sellingPrice;

    /**
     * Relation OneToMany vers DishIngredient (table de jointure avec extra colonnes).
     * orphanRemoval = true permet de supprimer les associations en effaçant la liste.
     */
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<DishIngredient> dishIngredients = new ArrayList<>();

    /** Calcule le coût total du plat (somme prix * quantité requise). */
    public Double getDishCost() {
        return dishIngredients.stream()
                .filter(di -> di.getIngredient() != null && di.getIngredient().getPrice() != null
                        && di.getQuantity() != null)
                .mapToDouble(di -> di.getIngredient().getPrice() * di.getQuantity())
                .sum();
    }

    /** Calcule la marge brute. Lève une exception si selling_price est null. */
    public Double getGrossMargin() {
        if (sellingPrice == null) {
            throw new RuntimeException("Dish.id=" + id + " has no selling price");
        }
        return sellingPrice - getDishCost();
    }
}