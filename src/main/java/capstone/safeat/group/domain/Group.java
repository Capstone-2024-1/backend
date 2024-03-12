package capstone.safeat.group.domain;

import static capstone.safeat.group.exception.GroupExceptionType.MEMBER_IS_ALREADY_CONTAIN;

import capstone.safeat.group.exception.GroupException;
import capstone.safeat.member.domain.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Group {

  private final Member creator;
  private final List<Member> members;

  public Group(final Member creator) {
    this.creator = creator;
    this.members = new ArrayList<>();
    members.add(creator);
  }

  public void addMember(final Member member) {
    validateIfExist(member);
    members.add(member);
  }

  private void validateIfExist(final Member member) {
    if (members.contains(member)) {
      throw new GroupException(MEMBER_IS_ALREADY_CONTAIN);
    }
  }
}
