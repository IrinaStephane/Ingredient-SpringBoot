package school.hei.ingredientspringboot.service;
import org.springframework.stereotype.Service;
import school.hei.ingredientspringboot.entity.Dish;
import school.hei.ingredientspringboot.entity.Ingredient;
import school.hei.ingredientspringboot.repository.DishRepository;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getDishes() {
        return dishRepository.getDishes();
    }

    public Dish getDishById(Integer id) {
        return dishRepository.getDishById(id);
    }

    public Dish updateDishIngredients(Integer dishId, List<Ingredient> ingredients) {
        return dishRepository.updateDishIngredients(dishId, ingredients);
    }
}