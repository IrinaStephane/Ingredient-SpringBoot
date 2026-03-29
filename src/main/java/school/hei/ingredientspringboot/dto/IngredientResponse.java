package school.hei.ingredientspringboot.dto;


import school.hei.ingredientspringboot.entity.CategoryEnum;
import school.hei.ingredientspringboot.entity.Ingredient;

/**
 * DTO de réponse pour un ingrédient.
 * Attributs attendus par TD5 : id, name, category, price.
 */
public record IngredientResponse(
        Integer id,
        String name,
        CategoryEnum category,
        Double price
) {
    /** Convertit une entité Ingredient en DTO de réponse. */
    public static IngredientResponse from(Ingredient ingredient) {
        return new IngredientResponse(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getCategory(),
                ingredient.getPrice()
        );
    }
}
