package school.hei.ingredientspringboot.dto;


import school.hei.ingredientspringboot.entity.Dish;
import school.hei.ingredientspringboot.entity.DishTypeEnum;

import java.util.List;

/**
 * DTO de réponse pour un plat.
 * Attributs attendus par TD5 : id, name, sellingPrice, ingredients.
 */
public record DishResponse(
        Integer id,
        String name,
        DishTypeEnum dishType,
        Double sellingPrice,
        List<IngredientResponse> ingredients
) {
    /** Convertit une entité Dish en DTO de réponse. */
    public static DishResponse from(Dish dish) {
        List<IngredientResponse> ingredients = dish.getDishIngredients().stream()
                .map(di -> IngredientResponse.from(di.getIngredient()))
                .toList();

        return new DishResponse(
                dish.getId(),
                dish.getName(),
                dish.getDishType(),
                dish.getSellingPrice(),
                ingredients
        );
    }
}
