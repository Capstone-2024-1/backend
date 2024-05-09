package capstone.safeat.member.application;

import static capstone.safeat.member.exception.MemberExceptionType.MEMBER_FORBIDDEN;

import capstone.safeat.category.domain.Category;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.exception.MemberException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberReader memberReader;
  private final MemberUpdater memberUpdater;

  private static void validateMember(final Long loginMemberId, final Member member) {
    if (!Objects.equals(member.getId(), loginMemberId)) {
      throw new MemberException(MEMBER_FORBIDDEN);
    }
  }

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
  public void editMemberNickName(
      final Long loginMemberId, final Long memberId, final String nickName
  ) {
    final Member member = memberReader.readMember(memberId);
    validateMember(loginMemberId, member);
    member.updateNickName(nickName);
  }

  private void addCategory(final List<Long> categoryIds, final Member member) {
    final List<Category> categories = Category.readAllById(categoryIds);
    memberUpdater.saveCategoryIntoMember(member, categories);
  }
}
