package capstone.safeat.category.dto;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.Religion;
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
        .convertHierarchy(categories);

    final Set<Long> flatChildIds = categoryResponses.stream()
        .map(ReligionResponse::getFlattenChildIds)
        .flatMap(List::stream)
        .collect(Collectors.toSet());

    return new ReligionResponse(
        religion.getId(), religion.getEnglishName(), religion.getKoreanName(), flatChildIds,
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
