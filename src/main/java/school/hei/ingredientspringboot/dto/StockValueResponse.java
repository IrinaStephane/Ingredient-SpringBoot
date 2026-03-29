package school.hei.ingredientspringboot.dto;


import school.hei.ingredientspringboot.entity.Unit;

/**
 * DTO de réponse pour le stock d'un ingrédient.
 * Attributs attendus par TD5 : unit, quantity.
 */
public record StockValueResponse(
        Unit unit,
        Double quantity
) {}
