package capstone.safeat.member.domain;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

  private final Long id;
  private final String nickName;
  private final String profileImageUrl;

  @Builder
  public Member(final Long id, final String nickName, final String profileImageUrl) {
    this.id = id;
    this.nickName = nickName;
    this.profileImageUrl = profileImageUrl;
  }

  @Builder
  public Member(final String nickName, final String profileImageUrl) {
    this(null, nickName, profileImageUrl);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Member member = (Member) o;
    if (member.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, member.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
