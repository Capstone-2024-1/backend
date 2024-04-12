package capstone.safeat.member.application;

import static capstone.safeat.fixture.domain.CategoryDomainFixture.과일;
import static capstone.safeat.fixture.domain.CategoryDomainFixture.망고;
import static capstone.safeat.fixture.domain.CategoryDomainFixture.사과;
import static capstone.safeat.fixture.domain.MemberFixture.멤버_1;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.CategoryRepository;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.RepositoryTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class MemberCategoryUpdaterTest extends RepositoryTest {

  @Autowired
  private MemberCategoryUpdater memberCategoryUpdater;
  @Autowired
  private MemberCategoryReader memberCategoryReader;

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private CategoryRepository categoryRepository;

  //TODO : 이 부분 시간 없어서 넘어갔는데 꼭 확인해보기 무슨 에러인지
  @Test
  @Transactional
  void 멤버에_카테고리를_추가한다() {
    //given
    final Member member = memberRepository.save(멤버_1());
    final Category parent = categoryRepository.save(과일());
    final Category apple = categoryRepository.save(사과(parent));
    final Category mango = categoryRepository.save(망고(parent));

    //when
    memberCategoryUpdater.saveMemberCategories(member, List.of(apple, mango));

    //then
    final List<Category> categories
        = memberCategoryReader.findCategoriesByMemberId(member.getId());

    assertThat(categories)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields()
        .containsExactlyInAnyOrder(apple, mango);
  }
}