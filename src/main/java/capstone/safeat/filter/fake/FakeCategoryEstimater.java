package capstone.safeat.filter.fake;

import capstone.safeat.filter.application.CategoryEstimater;
import capstone.safeat.filter.domain.EstimatedFood;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("local")
@Component
public class FakeCategoryEstimater implements CategoryEstimater {

  @Override
  public EstimatedFood estimateFood(final String foodName) {
    return EstimatedFood.builder().build();
  }
}
