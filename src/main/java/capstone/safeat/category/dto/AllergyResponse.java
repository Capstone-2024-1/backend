package capstone.safeat.category.dto;

import capstone.safeat.category.domain.Allergy;
import capstone.safeat.category.domain.Category;
import java.util.ArrayList;
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

    final List<CategoryResponse> categoryResponses = CategoryResponse
        .convertHierarchy(categories);

    final Set<Long> flatChildIds = categoryResponses.stream()
        .map(AllergyResponse::getFlattenChildIds)
        .flatMap(List::stream)
        .collect(Collectors.toSet());

    return new AllergyResponse(
        allergy.getId(), allergy.getEnglishName(), allergy.getKoreanName(),
        flatChildIds,
        categoryResponses
    );
  }

  private static List<Long> getFlattenChildIds(final CategoryResponse categoryResponse) {
    final List<Long> childIds = new ArrayList<>();
    for (final CategoryResponse childCategory : categoryResponse.getChildCategories()) {
      childIds.add(childCategory.getId());
      childIds.addAll(getFlattenChildIds(childCategory));
    }
    return childIds;
  }
}
