package capstone.safeat.filter.vo;

import capstone.safeat.category.domain.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

public class EstimatedFood {

  private final String koreanName;
  private final List<Category> categories;
  private final Boolean isAmbiguous;
  private final Boolean isFood;

  @Builder
  public EstimatedFood(
      final String koreanName, final List<Category> categories,
      final Boolean isAmbiguous, final Boolean isFood
  ) {
    this.koreanName = koreanName;
    this.categories = categories;
    this.isAmbiguous = isAmbiguous;
    this.isFood = isFood;
  }

  public String getKoreanName() {
    return koreanName;
  }

  public Boolean isAmbiguous() {
    return isAmbiguous;
  }

  public Boolean isFood() {
    return isFood;
  }

  public List<Category> extractCannotEatCategories(final List<Category> filterCategories) {
    return categories.stream()
        .filter(filterCategories::contains)
        .toList();
  }

  public List<Category> extractCanEatCategories(final List<Category> filterCategories) {
    final List<Category> categories = new ArrayList<>(this.categories);
    categories.removeAll(filterCategories);
    return categories;
  }
}
