package capstone.safeat.filter.dto;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.dto.CategoryResponse;
import capstone.safeat.filter.vo.EstimatedFood;
import java.util.List;

public record FoodFilterResponse(
    String koreanName,
    String englishName,
    List<CategoryResponse> cannotEatCategories,
    List<CategoryResponse> canEatCategories,
    boolean canEat,
    boolean isFood,
    boolean isAmbiguous
) {

  public static FoodFilterResponse of(
      final EstimatedFood estimatedFood, final String englishName,
      final List<Category> cannotEatCategories,
      final List<Category> canEatCategories

  ) {
    return new FoodFilterResponse(
        estimatedFood.getKoreanName(),
        englishName,
        CategoryResponse.generateList(cannotEatCategories),
        CategoryResponse.generateList(canEatCategories),
        cannotEatCategories.isEmpty(),
        estimatedFood.isFood(),
        estimatedFood.isAmbiguous()
    );
  }
}
