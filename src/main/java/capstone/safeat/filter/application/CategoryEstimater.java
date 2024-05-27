package capstone.safeat.filter.application;

import capstone.safeat.filter.vo.EstimatedFood;

public interface CategoryEstimater {

  EstimatedFood estimateFood(final String foodName);
}
