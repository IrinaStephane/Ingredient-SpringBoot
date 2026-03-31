package school.hei.ingredientspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.ingredientspringboot.entity.Ingredient;
import school.hei.ingredientspringboot.entity.StockMovement;
import school.hei.ingredientspringboot.entity.StockValue;
import school.hei.ingredientspringboot.entity.UnitEnum;
import school.hei.ingredientspringboot.service.IngredientService;

import java.time.Instant;
import java.util.List;

@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ResponseEntity.ok(ingredientService.getIngredients());
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient == null) {
            return ResponseEntity.status(404)
                    .body("Ingredient.id=" + id + " is not found");
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/ingredients/{id}/stock")
    public ResponseEntity<?> getIngredientStock(
            @PathVariable Integer id,
            @RequestParam(required = false) String at,
            @RequestParam(required = false) String unit) {

        if (at == null || unit == null) {
            return ResponseEntity.status(400)
                    .body("Either mandatory query parameter `at` or `unit` is not provided.");
        }

        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient == null) {
            return ResponseEntity.status(404)
                    .body("Ingredient.id=" + id + " is not found");
        }

        Instant atInstant;
        UnitEnum unitEnum;
        try {
            atInstant = Instant.parse(at);
        } catch (Exception e) {
            return ResponseEntity.status(400)
                    .body("Invalid value for `at` parameter. Expected ISO-8601 format.");
        }
        try {
            unitEnum = UnitEnum.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .body("Invalid value for `unit` parameter. Expected one of: PCS, KG, L.");
        }

        StockValue stockValue = ingredientService.getStockAt(id, atInstant, unitEnum);
        return ResponseEntity.ok(stockValue);
    }

    // f) GET /ingredients/{id}/stockMovements
    @GetMapping("/ingredients/{id}/stockMovements")
    public ResponseEntity<?> getStockMovements(
            @PathVariable Integer id,
            @RequestParam String from,
            @RequestParam String to) {

        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient == null) {
            return ResponseEntity.status(404)
                    .body("Ingredient.id=" + id + " is not found");
        }

        try {
            Instant fromInstant = Instant.parse(from);
            Instant toInstant = Instant.parse(to);
            List<StockMovement> movements = ingredientService.getStockMovements(id, fromInstant, toInstant);
            return ResponseEntity.ok(movements);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Invalid date format for 'from' or 'to'.");
        }
    }

    // g) POST /ingredients/{id}/stockMovements
    @PostMapping("/ingredients/{id}/stockMovements")
    public ResponseEntity<?> postStockMovements(
            @PathVariable Integer id,
            @RequestBody List<StockMovement> movements) {

        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient == null) {
            return ResponseEntity.status(404)
                    .body("Ingredient.id=" + id + " is not found");
        }

        List<StockMovement> saved = ingredientService.addStockMovements(id, movements);
        return ResponseEntity.ok(saved);
    }
}