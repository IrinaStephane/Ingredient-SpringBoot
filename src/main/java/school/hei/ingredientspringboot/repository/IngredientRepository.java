package school.hei.ingredientspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.hei.ingredientspringboot.entity.Ingredient;

import java.util.List;

/**
 * Remplace les méthodes findIngredient* de DataRetriever.
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    /** Vérifie si un ingrédient avec ce nom existe déjà (pour createIngredients). */
    boolean existsByNameIgnoreCase(String name);

    /** Cherche les ingrédients dont le nom contient la valeur donnée (ILIKE). */
    @Query("SELECT i FROM Ingredient i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Ingredient> findByNameContainingIgnoreCase(@Param("name") String name);
}