package capstone.safeat.category.dto;

import static capstone.safeat.category.dto.CategoryResponse.extractAllCategoryIds;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.Religion;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ReligionResponse {

  private final Long id;
  private final String englishName;
  private final String koreanName;
  private final Set<Long> flatChildIds;
  private final List<CategoryResponse> childCategories;

  public static List<ReligionResponse> convertHierarchy(final List<Religion> religions) {
    return religions.stream()
        .map(ReligionResponse::convertHierarchy)
        .toList();
  }

  private static ReligionResponse convertHierarchy(final Religion religion) {
    final List<Category> categories = religion.getChildren();

    final List<CategoryResponse> categoryResponses = CategoryResponse
        .convertHierarchyWithLeafs(categories);

    final Set<Long> flatChildIds = extractAllCategoryIds(categoryResponses);

    return new ReligionResponse(
        religion.getId(), religion.getEnglishName(), religion.getKoreanName(), flatChildIds,
        categoryResponses
    );
  }
}
