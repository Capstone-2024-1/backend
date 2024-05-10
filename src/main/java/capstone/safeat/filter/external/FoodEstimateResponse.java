package capstone.safeat.filter.external;

import capstone.safeat.category.domain.Category;
import capstone.safeat.filter.domain.EstimatedFood;
import capstone.safeat.filter.domain.EstimatedFood.EstimatedFoodBuilder;
import java.util.List;

public record FoodEstimateResponse(
    String koreanName, List<Ingredient> ingredients, boolean isAmbiguous, boolean isFood
) {

  public record Ingredient(String englishName) {

  }

  public EstimatedFood toEstimateCategory() {
    final List<Category> categories = ingredients().stream()
        .map(Ingredient::englishName)
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
