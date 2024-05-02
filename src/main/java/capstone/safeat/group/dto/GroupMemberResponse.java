package capstone.safeat.group.dto;

import capstone.safeat.member.domain.Member;
import java.util.List;

public record GroupMemberResponse(Long id, String name) {

  public static List<GroupMemberResponse> from(final List<Member> members) {
    return members.stream()
        .map(GroupMemberResponse::from)
        .toList();
  }

  private static GroupMemberResponse from(final Member member) {
    return new GroupMemberResponse(member.getId(), member.getNickName());
  }
}
