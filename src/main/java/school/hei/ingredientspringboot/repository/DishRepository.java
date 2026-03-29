package school.hei.ingredientspringboot.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.ingredientspringboot.entity.Dish;

import java.util.List;
import java.util.Optional;

/**
 * Remplace les méthodes findDish* et saveDish de DataRetriever.
 */
public interface DishRepository extends JpaRepository<Dish, Integer> {

    /**
     * Charge les plats avec leurs DishIngredient et Ingredient en une seule requête
     * pour éviter le problème N+1.
     */
    @EntityGraph(attributePaths = {"dishIngredients", "dishIngredients.ingredient"})
    List<Dish> findAll();

    @EntityGraph(attributePaths = {"dishIngredients", "dishIngredients.ingredient"})
    Optional<Dish> findWithIngredientsById(Integer id);
}
