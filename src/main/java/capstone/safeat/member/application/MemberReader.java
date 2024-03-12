package capstone.safeat.member.application;

import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberEntity;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.oauth.domain.OAuthMemberId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberReader {

  private final MemberRepository memberRepository;

  public Optional<Member> findByOAuthMemberId(final OAuthMemberId oauthMemberId) {
    return memberRepository.findByOauthMemberId(oauthMemberId)
        .map(MemberEntity::toDomain);
  }
}
