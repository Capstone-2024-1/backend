package capstone.safeat.category.application;

import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.MemberCategory;
import capstone.safeat.category.domain.MemberCategoryRepository;
import capstone.safeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryReaderTest extends ServiceTest {

  @Autowired
  private CategoryReader categoryReader;

  @Autowired
  private MemberCategoryRepository memberCategoryRepository;

  @Test
  void 멤버_아이디로_멤버에_추가된_카테고리들을_조회한다() {
    final List<Category> expected = List.of(Category.APPLE, Category.MANGO);
    final Long memberId = 1L;

    final List<MemberCategory> memberCategories = expected.stream()
        .map(category -> new MemberCategory(memberId, category))
        .toList();
    memberCategoryRepository.saveAll(memberCategories);

    final List<Category> actual = categoryReader.readCategoriesByMemberId(memberId);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("parent", "children")
        .containsExactlyInAnyOrderElementsOf(expected);
  }

  @Test
  void 멤버_아이디_여러개로_추가된_카테고리_들을_조회한다() {
    final List<Category> member1Categories = List.of(Category.APPLE, Category.MANGO);
    final List<Category> member2Categories = List.of(Category.APPLE, Category.DUCK);
    final List<Category> expected = List.of(Category.APPLE, Category.MANGO, Category.DUCK);

    final Long member1Id = 1L;
    final Long member2Id = 2L;

    final List<MemberCategory> memberCategories1 = member1Categories.stream()
        .map(category -> new MemberCategory(member1Id, category))
        .toList();
    memberCategoryRepository.saveAll(memberCategories1);

    final List<MemberCategory> memberCategories2 = member2Categories.stream()
        .map(category -> new MemberCategory(member2Id, category))
        .toList();
    memberCategoryRepository.saveAll(memberCategories2);

    final List<Category> actual = categoryReader.readAllCategoriesByMemberIds(
        List.of(member1Id, member2Id)
    );

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorOnFields()
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}