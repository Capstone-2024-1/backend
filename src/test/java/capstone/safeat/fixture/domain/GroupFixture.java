package capstone.safeat.fixture.domain;

import static capstone.safeat.fixture.domain.MemberFixture.멤버_홍혁준_생성;

import capstone.safeat.group.domain.Group;

public class GroupFixture {

  public static Group 새로운_그룹_생성() {
    return new Group(멤버_홍혁준_생성());
  }
}
