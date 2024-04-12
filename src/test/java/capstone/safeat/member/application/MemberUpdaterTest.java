package capstone.safeat.member.application;

import static capstone.safeat.fixture.domain.CategoryDomainFixture.과일;
import static capstone.safeat.fixture.domain.CategoryDomainFixture.망고;
import static capstone.safeat.fixture.domain.CategoryDomainFixture.사과;
import static capstone.safeat.fixture.domain.MemberFixture.멤버_1;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.CategoryRepository;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.RepositoryTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberUpdaterTest extends RepositoryTest {

  @Autowired
  private MemberUpdater memberUpdater;

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private CategoryReader categoryReader;

  @Test
  void 멤버에_카테고리를_추가한다() {
    //given
    final Member member = memberRepository.save(멤버_1());
    final Category parent = categoryRepository.save(과일());
    final Category apple = categoryRepository.save(사과(parent));
    final Category mango = categoryRepository.save(망고(parent));
    final List<Category> expected = List.of(apple, mango);

    //when
    memberUpdater.saveCategoryIntoMember(member, expected);

    //then
    final List<Category> actual
        = categoryReader.readCategoriesByMemberId(member.getId());

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("parent", "children")
        .containsExactlyInAnyOrder(apple, mango);
  }
}