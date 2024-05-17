package capstone.safeat.category.dto;

import capstone.safeat.category.domain.Category;
import java.util.List;

public record CategoryResponse(Long id, String englishName, String koreanName, String imageUrl) {

  public static List<CategoryResponse> generateList(final List<Category> categories) {
    return categories.stream()
        .map(CategoryResponse::from)
        .toList();
  }

  public static CategoryResponse from(final Category category) {
    return new CategoryResponse(
        category.getId(), category.getEnglishName(), category.getKoreanName(),
        category.getImageUrl()
    );
  }
}
