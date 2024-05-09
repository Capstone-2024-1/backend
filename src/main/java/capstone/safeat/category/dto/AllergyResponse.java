package capstone.safeat.category.dto;

import static capstone.safeat.category.dto.CategoryTreeResponse.extractAllCategoryIds;

import capstone.safeat.category.domain.Allergy;
import capstone.safeat.category.domain.Category;
import java.util.List;
import java.util.Set;
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
  private final List<CategoryTreeResponse> childCategories;

  public static List<AllergyResponse> convertHierarchy(final List<Allergy> allergies) {
    return allergies.stream()
        .map(AllergyResponse::convertHierarchy)
        .toList();
  }

  private static AllergyResponse convertHierarchy(final Allergy allergy) {
    final List<Category> categories = allergy.getChildren();

    final List<CategoryTreeResponse> categoryTreeRespons
        = CategoryTreeResponse.convertHierarchyWithLeafs(categories);

    final Set<Long> flatChildIds = extractAllCategoryIds(categoryTreeRespons);

    return new AllergyResponse(
        allergy.getId(), allergy.getEnglishName(), allergy.getKoreanName(),
        flatChildIds,
        categoryTreeRespons
    );
  }

}
