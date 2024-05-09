package capstone.safeat.member.application;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.MemberCategory;
import capstone.safeat.category.domain.MemberCategoryRepository;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class MemberUpdater {

  private final MemberRepository memberRepository;
  private final MemberCategoryRepository memberCategoryRepository;
  private final CategoryReader categoryReader;

  public Member registerNewMember(final OAuthMemberInfo oauthMemberInfo) {
    return memberRepository.save(Member.createOAuthMember(oauthMemberInfo));
  }

  public void saveCategoryIntoMember(final Member member, final List<Category> categories) {
    final List<MemberCategory> memberCategories = categories.stream()
        .filter(Category::isLeaf)
        .map(category -> new MemberCategory(member.getId(), category))
        .toList();
    memberCategoryRepository.saveAll(memberCategories);
  }

  public void setMemberCategories(final Member member, final List<Category> newCategories) {
    final List<Category> oldCategories = categoryReader.readCategoriesByMemberId(member.getId());

    final List<Category> removeCategories = new ArrayList<>(oldCategories);
    removeCategories.removeAll(newCategories);

    final List<Category> addCategories = new ArrayList<>(newCategories);
    addCategories.removeAll(oldCategories);

    saveCategoryIntoMember(member, addCategories);
    memberCategoryRepository.deleteAllBy(member.getId(), removeCategories);
  }
}
