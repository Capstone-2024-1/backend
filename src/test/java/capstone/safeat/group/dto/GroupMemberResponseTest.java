package capstone.safeat.group.dto;

import static capstone.safeat.fixture.docs.MemberFixture.멤버_김동우_생성;
import static capstone.safeat.fixture.docs.MemberFixture.멤버_전영은_생성;
import static capstone.safeat.fixture.docs.MemberFixture.멤버_홍혁준_생성;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.member.domain.Member;
import java.util.List;
import org.junit.jupiter.api.Test;

class GroupMemberResponseTest {

  @Test
  void 변환_테스트() {
    final Member 홍혁준 = 멤버_홍혁준_생성();
    final Member 전영은 = 멤버_전영은_생성();
    final Member 김동우 = 멤버_김동우_생성();
    final List<Member> members = List.of(홍혁준, 전영은, 김동우);
    final List<GroupMemberResponse> expected = List.of(
        new GroupMemberResponse(홍혁준.getId(), 홍혁준.getNickName()),
        new GroupMemberResponse(전영은.getId(), 전영은.getNickName()),
        new GroupMemberResponse(김동우.getId(), 김동우.getNickName())
    );

    final List<GroupMemberResponse> actual = GroupMemberResponse.from(members);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}