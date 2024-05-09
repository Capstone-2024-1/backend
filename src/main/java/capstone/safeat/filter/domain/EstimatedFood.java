package capstone.safeat.filter.domain;

import capstone.safeat.category.domain.Category;
import java.util.List;
import lombok.Builder;

public class EstimatedFood {

  private final String koreanName;
  private final String englishName;
  private final List<Category> categories;
  private final boolean isAmbiguous;
  private final boolean isFood;

  @Builder
  public EstimatedFood(
      final String koreanName, final String englishName, final List<Category> categories,
      final boolean isAmbiguous, final boolean isFood
  ) {
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.categories = categories;
    this.isAmbiguous = isAmbiguous;
    this.isFood = isFood;
  }

  public String getKoreanName() {
    return koreanName;
  }

  public String getEnglishName() {
    return englishName;
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
}
