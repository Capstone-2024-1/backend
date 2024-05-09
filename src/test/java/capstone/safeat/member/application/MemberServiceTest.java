package capstone.safeat.member.application;

import static capstone.safeat.category.domain.Category.APPLE;
import static capstone.safeat.category.domain.Category.HONEY;
import static capstone.safeat.category.domain.Category.POTATO;
import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;
import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.oauth.domain.OAuthMemberId;
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
  @Autowired
  private MemberReader memberReader;

  @Test
  void 멤버에_카테고리를_추가한다() {
    //given
    final Member member = memberRepository.save(멤버_홍혁준_생성());
    final List<Category> expected = List.of(APPLE, Category.MANGO);
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

  @Test
  void 멤버가_회원가입_한다() {
    //given
    final Member member = memberRepository.save(Member.builder()
        .profileImageUrl("프로필 이미지")
        .oauthMemberId(new OAuthMemberId("member1_id", GOOGLE))
        .build()
    );
    final List<Category> expected = List.of(APPLE, Category.MANGO);
    final List<Long> categoryIds = expected.stream()
        .map(Category::getId)
        .toList();
    final String nickName = "홍혁준";

    //when
    memberService.register(member.getId(), categoryIds, nickName);

    //then
    final List<Category> categories = categoryReader.readCategoriesByMemberId(member.getId());
    final Member readMember = memberReader.readMember(member.getId());

    assertAll(
        () -> assertThat(categories)
            .usingRecursiveFieldByFieldElementComparator()
            .containsExactlyInAnyOrderElementsOf(expected),
        () -> assertThat(readMember.getNickName())
            .isEqualTo(nickName),
        () -> assertThat(readMember.isRegistered())
            .isTrue()
    );
  }

  @Test
  void 멤버를_조회한다() {
    //given
    final Member member = memberRepository.save(멤버_홍혁준_생성());

    //when
    final Member foundMember = memberService.findMember(member.getId());

    //then
    assertThat(foundMember)
        .usingRecursiveComparison()
        .isEqualTo(member);
  }

  @Test
  void 멤버의_닉네임을_수정한다() {
    //given
    final Member member = memberRepository.save(멤버_홍혁준_생성());

    //when
    final String newNickName = "새로운 닉네임";
    memberService.editMemberNickName(member.getId(), newNickName);

    //then
    final Member foundMember = memberReader.readMember(member.getId());
    assertThat(foundMember.getNickName())
        .isEqualTo(newNickName);
  }

  @Test
  void 멤버의_카테고리들을_조회한다() {
    //given
    final Member member = memberRepository.save(멤버_홍혁준_생성());
    final List<Long> categoryIds = List.of(APPLE.getId(), POTATO.getId());

    memberService.addCategoryIntoMember(member.getId(), categoryIds);

    //when
    final List<Category> categories = memberService.getMemberCategories(member.getId());

    //then
    assertThat(categories)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrder(APPLE, POTATO);
  }

  @Test
  void 멤버의_카테고리를_덮어씌운다() {
    //given
    final Member member = memberRepository.save(멤버_홍혁준_생성());
    final List<Long> oldCategoryIds = List.of(APPLE.getId(), POTATO.getId());
    final List<Long> newCategoryIds = List.of(APPLE.getId(), HONEY.getId());

    memberService.addCategoryIntoMember(member.getId(), oldCategoryIds);

    //when
    memberService.setMemberCategories(member.getId(), newCategoryIds);

    //then
    final List<Category> categories = memberService.getMemberCategories(member.getId());
    assertThat(categories)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrder(APPLE, HONEY);
  }
}