package school.hei.ingredientspringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objet valeur représentant une quantité avec son unité.
 * Utilisé comme @Embeddable dans StockMovement.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockValue {

    @Column(name = "quantity")
    private Double quantity;

    // Note : si PostgreSQL lève une erreur de cast sur l'enum unit_type,
    // remplacez @Column par @Column(columnDefinition = "unit_type")
    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private Unit unit;
}
