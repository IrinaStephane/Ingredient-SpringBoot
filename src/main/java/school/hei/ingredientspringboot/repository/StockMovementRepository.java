package school.hei.ingredientspringboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.ingredientspringboot.entity.StockMovement;

import java.time.Instant;
import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {

    List<StockMovement> findByIngredientIdAndCreationDatetimeLessThanEqual(
            Integer ingredientId, Instant at);
}