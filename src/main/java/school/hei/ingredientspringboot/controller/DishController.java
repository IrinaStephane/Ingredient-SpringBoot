package school.hei.ingredientspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.ingredientspringboot.dto.DishResponse;
import school.hei.ingredientspringboot.dto.IngredientRequest;
import school.hei.ingredientspringboot.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishResponse>> getAll() {
        return ResponseEntity.ok(dishService.findAll());
    }

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
