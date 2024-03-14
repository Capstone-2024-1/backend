package capstone.safeat.group.domain;

import static capstone.safeat.group.exception.GroupExceptionType.MEMBER_IS_ALREADY_CONTAIN;

import capstone.safeat.group.exception.GroupException;
import capstone.safeat.group.exception.GroupExceptionType;
import capstone.safeat.member.domain.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Group {

  private static final String DEFAULT_GROUP_IMAGE_URL = "";

  private final Member creator;
  private final List<Member> members;
  private final String imageUrl;

  public Group(final Member creator) {
    this.creator = creator;
    this.members = new ArrayList<>();
    this.imageUrl = DEFAULT_GROUP_IMAGE_URL;
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

  public void drop(final Member member) {
    members.remove(member);
  }

  public void expel(final Member creator, final Member member) {
    if (!this.creator.equals(creator)) {
      throw new GroupException(GroupExceptionType.EXECUTORS_IS_NOT_CREATOR);
    }
    members.remove(member);
  }
}
