package capstone.safeat.member.application;

import capstone.safeat.member.domain.Member;
import capstone.safeat.member.dto.LoginResponse;
import capstone.safeat.oauth.application.OAuthMemberClientComposite;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import capstone.safeat.oauth.domain.OAuthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberService {

  private final OAuthMemberClientComposite oauthMemberClientComposite;
  private final JwtProvider jwtProvider;
  private final MemberReader memberReader;
  private final MemberUpdater memberUpdater;

  @Transactional
  public LoginResponse createToken(final String oauthType, final String code) {
    final OAuthServerType oauthServerType = OAuthServerType.fromName(oauthType);
    final OAuthMemberInfo oauthMemberInfo = oauthMemberClientComposite
        .fetchMemberInfo(oauthServerType, code);
    final Member member = memberReader.findByOAuthMemberId(oauthMemberInfo.oauthMemberId())
        .orElseGet(() -> memberUpdater.saveNewMember(oauthMemberInfo));
    return new LoginResponse(member.getId(), jwtProvider.createToken(member.getId()));
  }
}
