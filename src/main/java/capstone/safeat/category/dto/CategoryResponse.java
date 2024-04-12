package capstone.safeat.category.dto;

import static java.util.stream.Collectors.toMap;

import capstone.safeat.category.domain.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CategoryResponse {

  private final Long id;
  private final String englishName;
  private final String koreanName;
  //  private final List<Long> childIds = new ArrayList<>();
  private final List<CategoryResponse> childCategories;

  private static CategoryResponse fromWithEmptyChildren(final Category category) {
    return new CategoryResponse(
        category.getId(), category.getEnglishName(),
        category.getKoreanName(), new ArrayList<>()
    );
  }

  public static List<CategoryResponse> convertHierarchy(final List<Category> categories) {
    final Map<Long, CategoryResponse> responseMap = categories.stream()
        .collect(toMap(Category::getId, CategoryResponse::fromWithEmptyChildren));

    addChildren(categories, responseMap);

    return categories.stream()
        .filter(Category::isRootCategory)
        .map(category -> responseMap.get(category.getId()))
        .toList();
  }

  private static void addChildren(
      final List<Category> categories, final Map<Long, CategoryResponse> responseMap
  ) {
    for (final Category category : categories) {
      final Optional<Category> parent = category.getParent();
      if (parent.isPresent()) {
        final Long parentId = parent.get().getId();
        final CategoryResponse categoryResponse = responseMap.get(category.getId());
        responseMap.get(parentId).getChildCategories().add(categoryResponse);
      }
    }
  }
}
