package school.hei.ingredientspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.ingredientspringboot.dto.IngredientResponse;
import school.hei.ingredientspringboot.dto.StockValueResponse;
import school.hei.ingredientspringboot.entity.Unit;
import school.hei.ingredientspringboot.service.IngredientService;

import java.time.Instant;
import java.util.List;

/**
 * Contrôleur REST pour les ingrédients.
 * Expose les endpoints définis dans le TD5.
 *
 * Injection par constructeur via @RequiredArgsConstructor (Lombok).
 * Aucun @Autowired dans ce projet.
 */
@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    /**
     * GET /ingredients
     * Retourne la liste de tous les ingrédients.
     */
    @GetMapping
    public ResponseEntity<List<IngredientResponse>> getAll() {
        return ResponseEntity.ok(ingredientService.findAll());
    }

    /**
     * GET /ingredients/{id}
     * Retourne un ingrédient par son identifiant.
     * 404 si l'ingrédient n'est pas trouvé.
     */
    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponse> getById(@PathVariable Integer id) {
        // La NotFoundException est gérée par GlobalExceptionHandler → 404
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    /**
     * GET /ingredients/{id}/stock?at={temporal}&unit={unit}
     * Retourne la valeur du stock d'un ingrédient à un instant donné.
     *
     * - 400 si `at` ou `unit` est absent.
     * - 404 si l'ingrédient n'est pas trouvé.
     *
     * Exemple : GET /ingredients/1/stock?at=2024-01-06T12:00:00Z&unit=KG
     */
    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getStock(
            @PathVariable Integer id,
            @RequestParam(required = false) String at,
            @RequestParam(required = false) String unit
    ) {
        // Validation des paramètres obligatoires (retourne 400 explicitement)
        if (at == null || unit == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Either mandatory query parameter `at` or `unit` is not provided.");
        }

        // Parse des paramètres
        Instant instant = Instant.parse(at);
        Unit unitEnum = Unit.valueOf(unit.toUpperCase());

        StockValueResponse response = ingredientService.getStockValueAt(id, instant, unitEnum);
        return ResponseEntity.ok(response);
    }
}
