package capstone.safeat.member.application;

import static capstone.safeat.member.exception.MemberExceptionType.MEMBER_NOT_FOUND;

import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.member.exception.MemberException;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberReader {

  private final MemberRepository memberRepository;

  public Member readMember(final Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
  }

  public Optional<Member> readBy(final OAuthMemberInfo oauthMemberInfo) {
    return memberRepository.findByOauthMemberId(oauthMemberInfo.oauthMemberId());
  }
}
