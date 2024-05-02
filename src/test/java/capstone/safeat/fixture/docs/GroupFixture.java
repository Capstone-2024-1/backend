package capstone.safeat.fixture.docs;

import static capstone.safeat.fixture.docs.MemberFixture.멤버_전영은_생성;
import static capstone.safeat.fixture.docs.MemberFixture.멤버_홍혁준_생성;

import capstone.safeat.group.domain.GroupDomain;
import java.util.List;

public class GroupFixture {

  public static GroupDomain 새로운그룹_1() {
    return new GroupDomain(3L, "그룹_1", "imageUrl", 멤버_홍혁준_생성(), List.of(멤버_홍혁준_생성()));
  }

  public static GroupDomain 새로운그룹_2() {
    return new GroupDomain(4L, "그룹_2", "imageUrl", 멤버_전영은_생성(), List.of(멤버_전영은_생성(), 멤버_홍혁준_생성()));
  }
}
