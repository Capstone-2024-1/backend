package capstone.safeat.fixture.entity;

import capstone.safeat.group.domain.Group;
import capstone.safeat.member.domain.Member;

public class GroupFixture {

  public static Group 새로운_그룹_생성(final Member member) {
    return Group.create("그룹_1", member);
  }
}
