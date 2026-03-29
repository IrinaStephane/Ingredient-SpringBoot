package school.hei.ingredientspringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.hei.ingredientspringboot.dto.IngredientResponse;
import school.hei.ingredientspringboot.dto.StockValueResponse;
import school.hei.ingredientspringboot.entity.Ingredient;
import school.hei.ingredientspringboot.entity.StockMovement;
import school.hei.ingredientspringboot.entity.Unit;
import school.hei.ingredientspringboot.exception.NotFoundException;
import school.hei.ingredientspringboot.repository.IngredientRepository;
import school.hei.ingredientspringboot.repository.StockMovementRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    // Injection par constructeur généré par Lombok (@RequiredArgsConstructor)
    private final IngredientRepository ingredientRepository;
    private final StockMovementRepository stockMovementRepository;

    /** Retourne tous les ingrédients (GET /ingredients). */
    @Transactional(readOnly = true)
    public List<IngredientResponse> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(IngredientResponse::from)
                .toList();
    }

    /** Retourne un ingrédient par ID (GET /ingredients/{id}). */
    @Transactional(readOnly = true)
    public IngredientResponse findById(Integer id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient.id=" + id + " is not found"));
        return IngredientResponse.from(ingredient);
    }

    /**
     * Calcule la valeur du stock d'un ingrédient à un instant donné.
     * GET /ingredients/{id}/stock?at={temporal}&unit={unit}
     */
    @Transactional(readOnly = true)
    public StockValueResponse getStockValueAt(Integer ingredientId, Instant at, Unit unit) {
        // Vérifie que l'ingrédient existe
        if (!ingredientRepository.existsById(ingredientId)) {
            throw new NotFoundException("Ingredient.id=" + ingredientId + " is not found");
        }

        // Récupère les mouvements de stock jusqu'à l'instant demandé
        List<StockMovement> movements = stockMovementRepository
                .findByIngredientIdAndCreationDatetimeLessThanEqual(ingredientId, at);

        // Somme des IN moins les OUT
        double quantity = movements.stream()
                .mapToDouble(sm -> {
                    double qty = sm.getValue().getQuantity();
                    return switch (sm.getType()) {
                        case IN -> qty;
                        case OUT -> -qty;
                    };
                })
                .sum();

        return new StockValueResponse(unit, quantity);
    }
}