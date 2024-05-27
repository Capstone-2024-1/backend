package capstone.safeat.filter.external.dto;

import capstone.safeat.category.domain.Category;
import capstone.safeat.filter.vo.EstimatedFood;
import capstone.safeat.filter.external.dto.FoodEstimateResponse.Ingredient;
import java.util.List;

public record FoodInferenceResponse(
    String koreanName, List<Ingredient> ingredients
) {

  public EstimatedFood toEstimateCategory() {
    final List<Category> categories = ingredients().stream()
        .map(Ingredient::englishName)
        .filter(str -> !str.isBlank())
        .map(Category::fromEnglishName)
        .toList();

    return EstimatedFood.builder()
        .categories(categories)
        .koreanName(koreanName)
        .build();
  }
}
