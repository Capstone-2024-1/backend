package capstone.safeat.filter.external;

import capstone.safeat.filter.application.FoodRecipeInferencer;
import capstone.safeat.filter.domain.EstimatedFood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class DavincciFoodRecipeInferencer implements FoodRecipeInferencer {

  private final AiApiClient aiApiClient;

  @Override
  public EstimatedFood inferenceFood(final String foodName) {
    return aiApiClient.inference(creatParams(foodName))
        .toEstimateCategory();
  }

  private static MultiValueMap<String, String> creatParams(final String foodName) {
    final MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
    param.add("koreanName", foodName);
    return param;
  }
}
