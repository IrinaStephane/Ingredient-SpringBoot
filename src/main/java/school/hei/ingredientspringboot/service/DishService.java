package school.hei.ingredientspringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.hei.ingredientspringboot.dto.DishResponse;
import school.hei.ingredientspringboot.dto.IngredientRequest;
import school.hei.ingredientspringboot.entity.Dish;
import school.hei.ingredientspringboot.entity.DishIngredient;
import school.hei.ingredientspringboot.entity.Ingredient;
import school.hei.ingredientspringboot.exception.NotFoundException;
import school.hei.ingredientspringboot.repository.DishRepository;
import school.hei.ingredientspringboot.repository.IngredientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    /** Retourne tous les plats avec leurs ingrédients (GET /dishes). */
    @Transactional(readOnly = true)
    public List<DishResponse> findAll() {
        return dishRepository.findAll()
                .stream()
                .map(DishResponse::from)
                .toList();
    }

    @Transactional
    public DishResponse updateDishIngredients(Integer dishId, List<IngredientRequest> ingredientRequests) {
        // 1. Récupérer le plat (404 si non trouvé)
        Dish dish = dishRepository.findWithIngredientsById(dishId)
                .orElseThrow(() -> new NotFoundException("Dish.id=" + dishId + " is not found"));

        // 2. Récupérer uniquement les ingrédients existants en BDD (les autres sont ignorés)
        List<Integer> requestedIds = ingredientRequests.stream()
                .map(IngredientRequest::id)
                .toList();

        List<Ingredient> validIngredients = ingredientRepository.findAllById(requestedIds);

        // 3. Effacer les anciennes associations (orphanRemoval = true supprimera les DishIngredient)
        dish.getDishIngredients().clear();

        // 4. Créer les nouvelles associations
        for (Ingredient ingredient : validIngredients) {
            DishIngredient di = DishIngredient.builder()
                    .dish(dish)
                    .ingredient(ingredient)
                    // quantity et unit non fournis par l'endpoint → null
                    .build();
            dish.getDishIngredients().add(di);
        }

        // 5. Sauvegarder et retourner
        Dish saved = dishRepository.save(dish);
        return DishResponse.from(saved);
    }
}