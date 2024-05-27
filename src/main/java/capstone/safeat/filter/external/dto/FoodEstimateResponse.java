package capstone.safeat.filter.external.dto;

import capstone.safeat.category.domain.Category;
import capstone.safeat.filter.vo.EstimatedFood;
import java.util.List;

public record FoodEstimateResponse(
    String koreanName, List<Ingredient> ingredients, boolean isAmbiguous, boolean isFood
) {

  public record Ingredient(String englishName) {
  }

  public EstimatedFood toEstimateCategory() {
    final List<Category> categories = ingredients().stream()
        .map(Ingredient::englishName)
        .filter(str -> !str.isBlank())
        .map(Category::fromEnglishName)
        .toList();

    return EstimatedFood.builder()
        .categories(categories)
        .isFood(isFood)
        .koreanName(koreanName)
        .isAmbiguous(isAmbiguous)
        .build();
  }
}
