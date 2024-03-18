package capstone.safeat.group.application;

import static capstone.safeat.fixture.domain.MemberFixture.멤버_홍혁준_생성;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.ApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GroupServiceTest extends ApplicationTest {

  @Autowired
  private GroupRepository groupRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private GroupService groupService;

  @Test
  void 그룹을_생성한다() {
    final Member creator = memberRepository.save(멤버_홍혁준_생성());

    final Long createdGroupId = groupService.createGroup(creator.getId());

    final boolean exists = groupRepository.existsById(createdGroupId);
    assertThat(exists)
        .isTrue();
  }
}