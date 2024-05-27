package capstone.safeat.filter.application;

import capstone.safeat.filter.vo.EstimatedFood;

public interface FoodRecipeInferencer {

  EstimatedFood inferenceFood(final String foodName);
}
