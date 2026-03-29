package school.hei.ingredientspringboot.dto;


import school.hei.ingredientspringboot.entity.CategoryEnum;

/**
 * DTO de requête pour un ingrédient (corps de PUT /dishes/{id}/ingredients).
 * Les champs name, category, price sont ignorés si différents de la BDD (cf. TD5).
 */
public record IngredientRequest(
        Integer id,
        String name,
        CategoryEnum category,
        Double price
) {}