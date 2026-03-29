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

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientResponse>> getAll() {
        return ResponseEntity.ok(ingredientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.findById(id));
    }

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
