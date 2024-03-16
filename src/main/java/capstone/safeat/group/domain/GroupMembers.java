package capstone.safeat.group.domain;

import static capstone.safeat.group.exception.GroupExceptionType.MEMBER_IS_ALREADY_CONTAIN;
import static jakarta.persistence.CascadeType.PERSIST;

import capstone.safeat.group.exception.GroupException;
import capstone.safeat.member.domain.Member;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class GroupMembers {

  @OneToMany(mappedBy = "group", cascade = PERSIST, orphanRemoval = true)
  private List<GroupMember> groupMembers;

  public GroupMembers(final List<GroupMember> groupMembers) {
    this.groupMembers = groupMembers;
  }

  public GroupMembers() {
    this.groupMembers = new ArrayList<>();
  }

  public void addMember(final GroupMember groupMember) {
    validateIfExist(groupMember);
    groupMembers.add(groupMember);
  }

  private void validateIfExist(final GroupMember groupMember) {
    if (groupMembers.contains(groupMember)) {
      throw new GroupException(MEMBER_IS_ALREADY_CONTAIN);
    }
  }

  public void remove(final Member member) {

  }
}
