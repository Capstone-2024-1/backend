package capstone.safeat.category.dto;

import capstone.safeat.category.domain.Allergy;
import capstone.safeat.category.domain.Category;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class AllergyResponse {

  private final Long id;
  private final String englishName;
  private final String koreanName;
  private final Set<Long> flatChildIds;
  private final List<CategoryResponse> childCategories;

  public static List<AllergyResponse> convertHierarchy(final List<Allergy> allergies) {
    return allergies.stream()
        .map(AllergyResponse::convertHierarchy)
        .toList();
  }

  private static AllergyResponse convertHierarchy(final Allergy allergy) {
    final List<Category> categories = allergy.getChildren();

    final List<CategoryResponse> categoryResponses
        = CategoryResponse.convertHierarchyWithLeafs(categories);

    final Set<Long> flatChildIds = extractChildIds(categoryResponses);

    return new AllergyResponse(
        allergy.getId(), allergy.getEnglishName(), allergy.getKoreanName(),
        flatChildIds,
        categoryResponses
    );
  }

  private static Set<Long> extractChildIds(final List<CategoryResponse> categoryResponses) {
    final Set<Long> flatChildIds = categoryResponses.stream()
        .map(CategoryResponse::getFlatChildIds)
        .flatMap(Set::stream)
        .collect(Collectors.toSet());

    final List<Long> categoryIds = categoryResponses.stream()
        .map(CategoryResponse::getId)
        .toList();

    flatChildIds.addAll(categoryIds);

    return flatChildIds;
  }
}
