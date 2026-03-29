package school.hei.ingredientspringboot.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.ingredientspringboot.entity.Dish;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    @EntityGraph(attributePaths = {"dishIngredients", "dishIngredients.ingredient"})
    List<Dish> findAll();

    @EntityGraph(attributePaths = {"dishIngredients", "dishIngredients.ingredient"})
    Optional<Dish> findWithIngredientsById(Integer id);
}
