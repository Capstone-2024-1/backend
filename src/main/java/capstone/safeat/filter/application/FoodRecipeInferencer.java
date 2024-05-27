package capstone.safeat.filter.application;

import capstone.safeat.filter.domain.EstimatedFood;

public interface FoodRecipeInferencer {

  EstimatedFood inferenceFood(final String foodName);
}
