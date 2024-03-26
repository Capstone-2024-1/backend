package capstone.safeat.member.application;

import capstone.safeat.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

//  @Transactional
//  public LoginResponse createToken(final String oauthType, final String code) {
//    final OAuthServerType oauthServerType = OAuthServerType.fromName(oauthType);
//    final OAuthMemberInfo oauthMemberInfo = oauthMemberClientComposite
//        .fetchMemberInfo(oauthServerType, code);
//    final Member member = memberRepository.findByOauthMemberId(oauthMemberInfo.oauthMemberId())
//        .orElseGet(() -> memberRepository.save(Member.createOAuthMember(oauthMemberInfo)));
//    return new LoginResponse(
//        member.getId(),
//        jwtProvider.createAccessTokenWith(member.getId()),
//        member.isRegistered()
//    );
//  }
}
