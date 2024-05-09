package capstone.safeat.member.dto;

import capstone.safeat.category.domain.Category;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record MemberCategoryIdsResponse(Set<Long> categoryIds) {

  public static MemberCategoryIdsResponse from(final List<Category> categories) {
    final List<Category> parentCategories = categories.stream()
        .map(Category::getAllParent)
        .flatMap(List::stream)
        .toList();

    final Set<Long> categoryIds = Stream.of(categories, parentCategories)
        .flatMap(List::stream)
        .map(Category::getId)
        .collect(Collectors.toSet());

    return new MemberCategoryIdsResponse(categoryIds);
  }
}
