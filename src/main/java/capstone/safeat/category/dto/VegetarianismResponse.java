package capstone.safeat.category.dto;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.Vegetarianism;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class VegetarianismResponse {

  private final Long id;
  private final String englishName;
  private final String koreanName;
  private final Set<Long> flatChildIds;
  private final List<CategoryTreeResponse> childCategories;

  public static List<VegetarianismResponse> convertHierarchy(
      final List<Vegetarianism> vegetarians
  ) {
    return vegetarians.stream()
        .map(VegetarianismResponse::convertHierarchy)
        .toList();
  }

  private static VegetarianismResponse convertHierarchy(final Vegetarianism vegetarianism) {
    final List<Category> categories = vegetarianism.getChildren();

    final List<CategoryTreeResponse> categoryTreeRespons = CategoryTreeResponse
        .convertHierarchyWithLeafs(categories);

    final Set<Long> flatChildIds = CategoryTreeResponse.extractAllCategoryIds(categoryTreeRespons);

    return new VegetarianismResponse(
        vegetarianism.getId(), vegetarianism.getEnglishName(), vegetarianism.getKoreanName(),
        flatChildIds,
        categoryTreeRespons
    );
  }
}
