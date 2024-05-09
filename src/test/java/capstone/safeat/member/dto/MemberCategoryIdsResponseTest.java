package capstone.safeat.member.dto;

import static capstone.safeat.category.domain.Category.APPLE;
import static capstone.safeat.category.domain.Category.FRUITS;
import static capstone.safeat.category.domain.Category.POTATO;
import static capstone.safeat.category.domain.Category.ROOT_VEGETABLES;
import static capstone.safeat.category.domain.Category.VEGETABLES;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Category;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class MemberCategoryIdsResponseTest {

  @Test
  void 멤버_카테고리_ID_목록_반환하기() {
    final List<Category> categories = List.of(Category.APPLE, Category.POTATO);
    final MemberCategoryIdsResponse expected = new MemberCategoryIdsResponse(
        Set.of(FRUITS.getId(), APPLE.getId(), POTATO.getId(), VEGETABLES.getId(),
            ROOT_VEGETABLES.getId())
    );

    final MemberCategoryIdsResponse actual = MemberCategoryIdsResponse.from(categories);

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}