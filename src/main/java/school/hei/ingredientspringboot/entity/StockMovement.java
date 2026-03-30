package school.hei.ingredientspringboot.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {
    private int              id;
    private StockValue       value;
    private MovementTypeEnum type;
    private Instant          creationDatetime;
}