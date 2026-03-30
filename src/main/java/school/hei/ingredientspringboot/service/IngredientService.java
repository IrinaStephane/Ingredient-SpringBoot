package school.hei.ingredientspringboot.service;
import org.springframework.stereotype.Service;
import school.hei.ingredientspringboot.entity.Ingredient;
import school.hei.ingredientspringboot.entity.StockValue;
import school.hei.ingredientspringboot.entity.UnitEnum;
import school.hei.ingredientspringboot.repository.IngredientRepository;

import java.time.Instant;
import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final StockMovementService stockMovementService;

    public IngredientService(IngredientRepository ingredientRepository,
                             StockMovementService stockMovementService) {
        this.ingredientRepository = ingredientRepository;
        this.stockMovementService = stockMovementService;
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepository.getIngredients();
    }

    public Ingredient getIngredientById(Integer id) {
        return ingredientRepository.getIngredientById(id);
    }

    public StockValue getStockAt(Integer ingredientId, Instant at, UnitEnum unit) {
        return stockMovementService.getStockValueAt(ingredientId, at, unit);
    }
}