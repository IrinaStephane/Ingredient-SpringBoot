package school.hei.ingredientspringboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.ingredientspringboot.entity.StockMovement;

import java.time.Instant;
import java.util.List;

/**
 * Repository pour les mouvements de stock (TD4).
 */
public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {

    /**
     * Récupère tous les mouvements d'un ingrédient jusqu'à un instant donné.
     * Utilisé pour calculer le stock à un moment précis.
     */
    List<StockMovement> findByIngredientIdAndCreationDatetimeLessThanEqual(
            Integer ingredientId, Instant at);
}