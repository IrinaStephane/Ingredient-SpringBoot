package school.hei.ingredientspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dish_ingredient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dish", nullable = false)
    private Dish dish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ingredient", nullable = false)
    private Ingredient ingredient;

    @Column(name = "required_quantity")
    private Double quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private Unit unit;
}