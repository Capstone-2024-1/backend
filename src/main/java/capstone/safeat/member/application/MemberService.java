package capstone.safeat.member.application;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.member.domain.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberReader memberReader;
  private final MemberUpdater memberUpdater;
  private final CategoryReader categoryReader;

  @Transactional
  public void addCategoryIntoMember(final Long memberId, final List<Long> categoryIds) {
    final Member member = memberReader.readMember(memberId);
    addCategory(categoryIds, member);
  }

  @Transactional
  public void register(final Long memberId, final List<Long> categoryIds, final String nickName) {
    final Member member = memberReader.readMember(memberId);
    member.register(nickName);
    addCategory(categoryIds, member);
  }

  @Transactional(readOnly = true)
  public Member findMember(final Long memberId) {
    return memberReader.readMember(memberId);
  }

  @Transactional
  public void editMemberNickName(final Long memberId, final String nickName) {
    final Member member = memberReader.readMember(memberId);
    member.updateNickName(nickName);
  }

  @Transactional(readOnly = true)
  public List<Category> getMemberCategories(final Long memberId) {
    return categoryReader.readCategoriesByMemberId(memberId);
  }

  @Transactional
  public void setMemberCategories(final Long memberId, final List<Long> categoryIds) {
    final Member member = memberReader.readMember(memberId);
    final List<Category> newCategories = Category.readAllById(categoryIds);
    memberUpdater.setMemberCategories(member, newCategories);
  }

  private void addCategory(final List<Long> categoryIds, final Member member) {
    final List<Category> categories = Category.readAllById(categoryIds);
    memberUpdater.saveCategoryIntoMember(member, categories);
  }
}
