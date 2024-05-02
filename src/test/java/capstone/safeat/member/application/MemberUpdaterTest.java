package capstone.safeat.member.application;

import static capstone.safeat.fixture.domain.MemberFixture.멤버_1;
import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
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
  private MemberReader memberReader;
  @Autowired
  private CategoryReader categoryReader;

  @Test
  void 멤버에_카테고리를_추가한다() {
    //given
    final Member member = memberRepository.save(멤버_1());
    final List<Category> expected = List.of(Category.APPLE, Category.MANGO);

    //when
    memberUpdater.saveCategoryIntoMember(member, expected);

    //then
    final List<Category> actual
        = categoryReader.readCategoriesByMemberId(member.getId());

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("parent", "children")
        .containsExactlyInAnyOrderElementsOf(expected);
  }

  @Test
  void 멤버가_회원가입한다() {
    final OAuthMemberInfo info = new OAuthMemberInfo("1234567890", "image", GOOGLE);

    final Member expected = memberUpdater.registerNewMember(info);

    final Member actual = memberReader.readMember(expected.getId());
    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}