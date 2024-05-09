package capstone.safeat.category.dto;

import static java.util.stream.Collectors.toMap;

import capstone.safeat.category.domain.Category;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CategoryTreeResponse {

  private final Long id;
  private final String englishName;
  private final String koreanName;
  private final Set<Long> flatChildIds;
  private final List<CategoryTreeResponse> childCategories;

  public static CategoryTreeResponse fromWithEmptyChildren(final Category category) {
    return new CategoryTreeResponse(
        category.getId(), category.getEnglishName(),
        category.getKoreanName(), new HashSet<>(), new ArrayList<>()
    );
  }

  public static List<CategoryTreeResponse> convertHierarchyWithAll(
      final List<Category> categories) {
    final Map<Long, CategoryTreeResponse> responseMap = categories.stream()
        .collect(toMap(Category::getId, CategoryTreeResponse::fromWithEmptyChildren));

    addChildrenCategories(categories, responseMap);
    addFlatChildIds(responseMap.values().stream().toList());

    return categories.stream()
        .filter(Category::isRootCategory)
        .map(category -> responseMap.get(category.getId()))
        .toList();
  }

  public static List<CategoryTreeResponse> convertHierarchyWithLeafs(
      final List<Category> categories) {
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

  private static void addFlatChildIds(final List<CategoryTreeResponse> categoryTreeRespons) {
    for (final CategoryTreeResponse categoryTreeResponse : categoryTreeRespons) {
      final List<Long> childIds = getFlattenChildIds(categoryTreeResponse);
      categoryTreeResponse.flatChildIds.addAll(childIds);
    }
  }

  private static List<Long> getFlattenChildIds(final CategoryTreeResponse categoryTreeResponse) {
    final List<Long> childIds = new ArrayList<>();
    for (final CategoryTreeResponse childCategory : categoryTreeResponse.getChildCategories()) {
      childIds.add(childCategory.getId());
      childIds.addAll(getFlattenChildIds(childCategory));
    }
    return childIds;
  }

  private static void addChildrenCategories(
      final List<Category> categories, final Map<Long, CategoryTreeResponse> responseMap
  ) {
    for (final Category category : categories) {
      final Optional<Category> parent = category.getParent();
      if (parent.isPresent()) {
        final Long parentId = parent.get().getId();
        final CategoryTreeResponse categoryTreeResponse = responseMap.get(category.getId());
        Optional.ofNullable(responseMap.get(parentId))
            .ifPresent(parentResponse -> parentResponse.getChildCategories().add(
                categoryTreeResponse));
      }
    }
  }

  public static Set<Long> extractAllCategoryIds(
      final List<CategoryTreeResponse> categoryTreeRespons) {
    final Set<Long> flatChildIds = categoryTreeRespons.stream()
        .map(CategoryTreeResponse::getFlatChildIds)
        .flatMap(Set::stream)
        .collect(Collectors.toSet());

    final List<Long> categoryIds = categoryTreeRespons.stream()
        .map(CategoryTreeResponse::getId)
        .toList();

    flatChildIds.addAll(categoryIds);

    return flatChildIds;
  }
}
