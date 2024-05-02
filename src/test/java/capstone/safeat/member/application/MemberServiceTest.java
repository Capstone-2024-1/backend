package capstone.safeat.member.application;

import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberServiceTest extends ServiceTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private CategoryReader categoryReader;

  @Test
  void 멤버에_카테고리를_추가한다() {
    //given
    final Member member = memberRepository.save(멤버_홍혁준_생성());
    final List<Category> expected = List.of(Category.APPLE, Category.MANGO);
    final List<Long> categoryIds = expected.stream()
        .map(Category::getId)
        .toList();

    //when
    memberService.addCategoryIntoMember(member.getId(), categoryIds);

    //then
    final List<Category> categories = categoryReader.readCategoriesByMemberId(member.getId());

    assertThat(categories)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("parent", "children")
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}