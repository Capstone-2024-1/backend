package capstone.safeat.fixture.entity;

import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;

import capstone.safeat.group.domain.Group;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;

public class GroupFixture {

  public static Group 새로운_그룹_생성(final Member member) {
    return Group.create("그룹_1", member);
  }
}
