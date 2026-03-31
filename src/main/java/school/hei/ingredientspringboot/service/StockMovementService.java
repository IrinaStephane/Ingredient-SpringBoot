package school.hei.ingredientspringboot.service;

import org.springframework.stereotype.Service;
import school.hei.ingredientspringboot.entity.StockMovement;
import school.hei.ingredientspringboot.entity.StockValue;
import school.hei.ingredientspringboot.entity.UnitEnum;
import school.hei.ingredientspringboot.repository.StockMovementRepository;

import java.time.Instant;
import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    public List<StockMovement> getMovements(Integer ingredientId, Instant from, Instant to) {
        return stockMovementRepository.findAllByIngredientIdAndPeriod(ingredientId, from, to);
    }

    public List<StockMovement> addMovements(Integer ingredientId, List<StockMovement> movements) {
        return stockMovementRepository.saveAll(ingredientId, movements);
    }

    public StockValue getStockValueAt(Integer ingredientId, Instant at, UnitEnum unit) {
        return stockMovementRepository.getStockValueAt(ingredientId, at, unit);
    }
}