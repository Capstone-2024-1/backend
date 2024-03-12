package capstone.safeat.member.domain;

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
}
