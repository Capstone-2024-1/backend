package capstone.safeat.filter.domain;

import capstone.safeat.category.domain.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

public class EstimatedFood {

  private final String koreanName;
  private final List<Category> categories;
  private final boolean isAmbiguous;
  private final boolean isFood;

  @Builder
  public EstimatedFood(
      final String koreanName, final List<Category> categories,
      final boolean isAmbiguous, final boolean isFood
  ) {
    this.koreanName = koreanName;
    this.categories = categories;
    this.isAmbiguous = isAmbiguous;
    this.isFood = isFood;
  }

  public String getKoreanName() {
    return koreanName;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public boolean isAmbiguous() {
    return isAmbiguous;
  }

  public boolean isFood() {
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
