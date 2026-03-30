package school.hei.ingredientspringboot.service;

import org.springframework.stereotype.Service;
import school.hei.ingredientspringboot.entity.StockValue;
import school.hei.ingredientspringboot.entity.UnitEnum;
import school.hei.ingredientspringboot.repository.StockMovementRepository;

import java.time.Instant;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    public StockValue getStockValueAt(Integer ingredientId, Instant at, UnitEnum unit) {
        return stockMovementRepository.getStockValueAt(ingredientId, at, unit);
    }
}