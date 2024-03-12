package capstone.safeat.member.domain;

import capstone.safeat.oauth.domain.OAuthMemberId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  Optional<MemberEntity> findByOauthMemberId(final OAuthMemberId oauthMemberId);
}
