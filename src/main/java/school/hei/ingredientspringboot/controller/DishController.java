package school.hei.ingredientspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.ingredientspringboot.dto.DishResponse;
import school.hei.ingredientspringboot.dto.IngredientRequest;
import school.hei.ingredientspringboot.service.DishService;

import java.util.List;

/**
 * Contrôleur REST pour les plats.
 * Expose les endpoints définis dans le TD5.
 *
 * Injection par constructeur via @RequiredArgsConstructor (Lombok).
 * Aucun @Autowired dans ce projet.
 */
@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    /**
     * GET /dishes
     * Retourne la liste de tous les plats avec leurs ingrédients.
     */
    @GetMapping
    public ResponseEntity<List<DishResponse>> getAll() {
        return ResponseEntity.ok(dishService.findAll());
    }

    /**
     * PUT /dishes/{id}/ingredients
     * Associe ou dissocie une liste d'ingrédients à un plat.
     *
     * - 400 si le corps de la requête est absent ou vide.
     * - 404 si le plat n'est pas trouvé.
     * - Les ingrédients non trouvés en BDD sont ignorés.
     */
    @PutMapping("/{id}/ingredients")
    public ResponseEntity<?> updateIngredients(
            @PathVariable Integer id,
            @RequestBody(required = false) List<IngredientRequest> ingredientRequests
    ) {
        // Corps obligatoire → 400 si absent
        if (ingredientRequests == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Request body with ingredient list is required.");
        }

        // La NotFoundException est gérée par GlobalExceptionHandler → 404
        DishResponse response = dishService.updateDishIngredients(id, ingredientRequests);
        return ResponseEntity.ok(response);
    }
}
