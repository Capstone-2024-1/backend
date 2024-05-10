package capstone.safeat.filter.external;

import capstone.safeat.filter.application.CategoryEstimater;
import capstone.safeat.filter.domain.EstimatedFood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class FaissCategoryEstimater implements CategoryEstimater {

  private final FaissApiClient faissApiClient;

  @Override
  public EstimatedFood estimateFood(final String foodName) {
    return faissApiClient.estimate(creatParams(foodName))
        .toEstimateCategory();
  }

  private static MultiValueMap<String, String> creatParams(final String foodName) {
    final MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
    param.add("koreanName", foodName);
    return param;
  }
}
