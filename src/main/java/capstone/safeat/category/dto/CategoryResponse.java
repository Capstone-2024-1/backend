package capstone.safeat.category.dto;

import static java.util.stream.Collectors.toMap;

import capstone.safeat.category.domain.Category;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
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
  private final Set<Long> flatChildIds;
  private final List<CategoryResponse> childCategories;

  public static CategoryResponse fromWithEmptyChildren(final Category category) {
    return new CategoryResponse(
        category.getId(), category.getEnglishName(),
        category.getKoreanName(), new HashSet<>(), new ArrayList<>()
    );
  }

  public static List<CategoryResponse> convertHierarchyWithAll(final List<Category> categories) {
    final Map<Long, CategoryResponse> responseMap = categories.stream()
        .collect(toMap(Category::getId, CategoryResponse::fromWithEmptyChildren));

    addChildrenCategories(categories, responseMap);
    addFlatChildIds(responseMap.values().stream().toList());

    return categories.stream()
        .filter(Category::isRootCategory)
        .map(category -> responseMap.get(category.getId()))
        .toList();
  }

  public static List<CategoryResponse> convertHierarchyWithLeafs(final List<Category> categories) {
    final List<Category> parentCategories = new ArrayList<>();
    for (final Category category : categories) {
      final List<Category> parents = category.getAllParent();
      parentCategories.addAll(parents);
    }

    final List<Category> categoriesIncludeParent = Stream.of(parentCategories, categories)
        .flatMap(List::stream)
        .distinct()
        .toList();

    return convertHierarchyWithAll(categoriesIncludeParent);
  }

  private static void addFlatChildIds(final List<CategoryResponse> categoryResponses) {
    for (final CategoryResponse categoryResponse : categoryResponses) {
      final List<Long> childIds = getFlattenChildIds(categoryResponse);
      categoryResponse.flatChildIds.addAll(childIds);
    }
  }

  private static List<Long> getFlattenChildIds(final CategoryResponse categoryResponse) {
    final List<Long> childIds = new ArrayList<>();
    for (final CategoryResponse childCategory : categoryResponse.getChildCategories()) {
      childIds.add(childCategory.getId());
      childIds.addAll(getFlattenChildIds(childCategory));
    }
    return childIds;
  }

  private static void addChildrenCategories(
      final List<Category> categories, final Map<Long, CategoryResponse> responseMap
  ) {
    for (final Category category : categories) {
      final Optional<Category> parent = category.getParent();
      if (parent.isPresent()) {
        final Long parentId = parent.get().getId();
        final CategoryResponse categoryResponse = responseMap.get(category.getId());
        Optional.ofNullable(responseMap.get(parentId))
            .ifPresent(parentResponse -> parentResponse.getChildCategories().add(categoryResponse));
      }
    }
  }
}
