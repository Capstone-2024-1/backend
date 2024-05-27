package capstone.safeat.filter.fake;

import capstone.safeat.filter.application.FoodRecipeInferencer;
import capstone.safeat.filter.vo.EstimatedFood;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("local")
@Component
public class FakeFoodInferencer implements FoodRecipeInferencer {

  @Override
  public EstimatedFood inferenceFood(final String foodName) {
    return EstimatedFood.builder().build();
  }
}
