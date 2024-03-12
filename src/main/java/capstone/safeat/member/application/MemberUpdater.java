package capstone.safeat.member.application;

import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberEntity;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUpdater {

  private final MemberRepository memberRepository;

  public Member saveNewMember(final OAuthMemberInfo oauthMemberInfo) {
    final MemberEntity memberEntity = MemberEntity.createOAuthMember(oauthMemberInfo);
    return memberRepository.save(memberEntity).toDomain();
  }
}
