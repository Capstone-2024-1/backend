package capstone.safeat.filter.external;

import capstone.safeat.filter.application.FoodRecipeInferencer;
import capstone.safeat.filter.vo.EstimatedFood;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Profile("prod")
@Component
@RequiredArgsConstructor
public class DavincciFoodRecipeInferencer implements FoodRecipeInferencer {

  private final Map<String, EstimatedFood> cache = new HashMap<>();
  private final AiApiClient aiApiClient;

  @Override
  public EstimatedFood inferenceFood(final String foodName) {
    return cache.computeIfAbsent(foodName,
        key -> aiApiClient.inference(creatParams(key))
            .toEstimateCategory()
    );
  }

  private static MultiValueMap<String, String> creatParams(final String foodName) {
    final MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
    param.add("koreanName", foodName);
    return param;
  }
}
