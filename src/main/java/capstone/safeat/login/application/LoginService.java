package capstone.safeat.login.application;

import capstone.safeat.login.dto.LoginResponse;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.oauth.application.OAuthMemberClientComposite;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import capstone.safeat.oauth.domain.OAuthServerType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final OAuthMemberClientComposite oauthMemberClientComposite;
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;

  @Transactional
  public LoginResponse createToken(final String oauthType, final String code) {
    final OAuthServerType oauthServerType = OAuthServerType.fromName(oauthType);
    final OAuthMemberInfo oauthMemberInfo = oauthMemberClientComposite
        .fetchMemberInfo(oauthServerType, code);
    final Member member = memberRepository.findByOauthMemberId(oauthMemberInfo.oauthMemberId())
        .orElseGet(() -> memberRepository.save(Member.createOAuthMember(oauthMemberInfo)));
    return new LoginResponse(
        member.getId(),
        jwtProvider.createAccessTokenWith(member.getId()),
        member.isRegistered()
    );
  }
}
