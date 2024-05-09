package capstone.safeat.filter.application;

import capstone.safeat.filter.domain.EstimatedFood.EstimatedFoodBuilder;

public interface CategoryEstimater {

  EstimatedFoodBuilder estimateFood(final String foodName);
}
