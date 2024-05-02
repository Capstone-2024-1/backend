package capstone.safeat.fixture.entity;

import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;

import capstone.safeat.group.domain.GroupEntity;

public class GroupFixture {

  public static GroupEntity 새로운_그룹_생성() {
    return GroupEntity.create("그룹_1", 멤버_홍혁준_생성());
  }
}
