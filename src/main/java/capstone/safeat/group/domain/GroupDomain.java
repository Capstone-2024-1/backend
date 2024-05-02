package capstone.safeat.group.domain;

import capstone.safeat.member.domain.Member;
import java.util.List;
import lombok.Getter;

@Getter
public class GroupDomain {

  private final Long id;
  private final String name;
  private final String imageUrl;
  private final Member creator;
  private final List<Member> members;

  public GroupDomain(
      final Long id, final String name, final String imageUrl, final Member creator,
      final List<Member> members
  ) {
    this.id = id;
    this.name = name;
    this.imageUrl = imageUrl;
    this.creator = creator;
    this.members = members;
  }
}
