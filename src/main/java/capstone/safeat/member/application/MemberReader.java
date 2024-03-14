package capstone.safeat.member.application;

import static capstone.safeat.member.exception.MemberExceptionType.MEMBER_NOT_FOUND;

import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberEntity;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.member.exception.MemberException;
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

  public Member findById(final Long memberId) {
    return memberRepository.findById(memberId)
        .map(MemberEntity::toDomain)
        .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
  }
}
