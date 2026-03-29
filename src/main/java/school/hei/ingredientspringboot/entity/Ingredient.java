package school.hei.ingredientspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un ingrédient.
 * Migré depuis la classe JDBC Ingredient.
 * Remplace aussi la gestion DataRetriever#findIngredientById.
 */
@Entity
@Table(name = "ingredient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private Double price;

    // Note : si PostgreSQL lève une erreur de cast, ajouter :
    // @Column(columnDefinition = "ingredient_category")
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private CategoryEnum category;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<StockMovement> stockMovements = new ArrayList<>();

    /**
     * Calcule la valeur du stock de cet ingrédient à un instant donné.
     * Reprend la logique de getStockValueAt(Instant) du TD4.
     */
    public StockValue getStockValueAt(Instant at, Unit unit) {
        double quantity = stockMovements.stream()
                .filter(sm -> !sm.getCreationDatetime().isAfter(at))
                .mapToDouble(sm ->
                        sm.getType() == MovementTypeEnum.IN
                                ? sm.getValue().getQuantity()
                                : -sm.getValue().getQuantity()
                )
                .sum();
        return new StockValue(quantity, unit);
    }
}