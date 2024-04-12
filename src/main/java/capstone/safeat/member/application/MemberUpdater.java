package capstone.safeat.member.application;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.MemberCategory;
import capstone.safeat.category.domain.MemberCategoryRepository;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class MemberUpdater {

  private final MemberRepository memberRepository;
  private final MemberCategoryRepository memberCategoryRepository;

  public Member registerNewMember(final OAuthMemberInfo oauthMemberInfo) {
    return memberRepository.save(Member.createOAuthMember(oauthMemberInfo));
  }

  public void saveCategoryIntoMember(final Member member, final List<Category> leafCategories) {
    final List<MemberCategory> memberCategories = leafCategories.stream()
        .map(category -> new MemberCategory(member.getId(), category))
        .toList();
    memberCategoryRepository.saveAll(memberCategories);
  }
}
