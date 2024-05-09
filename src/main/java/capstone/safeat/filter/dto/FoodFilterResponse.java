package capstone.safeat.filter.dto;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.dto.CategoryResponse;
import capstone.safeat.filter.domain.EstimatedFood;
import java.util.List;

public record FoodFilterResponse(
    String koreanName,
    String englishName,
    boolean canEat,
    List<CategoryResponse> cannotEatCategories,
    List<CategoryResponse> canEatCategories,
    boolean isFood,
    boolean isAmbiguous
) {

  //TODO :FilterService로 옮기기
  public static FoodFilterResponse of(
      final EstimatedFood estimatedFood, final List<Category> filterCategories
  ) {
    final List<Category> cannotEatCategories =
        extractCannotEatCategories(estimatedFood.getCategories(), filterCategories);
    return null;
  }

  private static List<Category> extractCannotEatCategories(
      final List<Category> categories, final List<Category> filterCategories
  ) {
    return null;
  }
}
