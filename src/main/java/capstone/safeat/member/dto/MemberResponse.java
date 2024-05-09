package capstone.safeat.member.dto;

import capstone.safeat.member.domain.Member;

public record MemberResponse(String nickName, String profileImageUrl) {

  public static MemberResponse from(final Member member) {
    return new MemberResponse(member.getNickName(), member.getProfileImageUrl());
  }
}
