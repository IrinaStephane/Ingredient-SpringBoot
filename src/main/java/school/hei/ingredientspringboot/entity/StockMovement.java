package school.hei.ingredientspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "stock_movement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingredient", nullable = false)
    private Ingredient ingredient;

    @Embedded
    private StockValue value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MovementTypeEnum type;

    @Column(name = "creation_datetime", nullable = false)
    private Instant creationDatetime;
}
