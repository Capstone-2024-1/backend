package capstone.safeat.member.application;

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

  @Transactional
  public void addCategoryIntoMember(final Long memberId, final List<Long> categoryIds) {
    final Member member = memberReader.readMember(memberId);
    final List<Category> categories = Category.readAllById(categoryIds);
    memberUpdater.saveCategoryIntoMember(member, categories);
  }
}
