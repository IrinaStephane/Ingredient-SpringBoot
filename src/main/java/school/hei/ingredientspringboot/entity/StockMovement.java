package school.hei.ingredientspringboot.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {
    private Integer id;
    private Instant creationDatetime;
    private UnitEnum unit;
    private Double quantity;
    private MovementTypeEnum type;
}