package capstone.safeat.filter.application;

import capstone.safeat.filter.domain.EstimatedFood;

public interface CategoryEstimater {

  EstimatedFood estimateFood(final String foodName);
}
