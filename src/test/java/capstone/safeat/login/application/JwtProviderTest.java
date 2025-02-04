package capstone.safeat.login.application;

import static org.assertj.core.api.Assertions.assertThat;

import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.io.Encoders;
import javax.crypto.SecretKey;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class JwtProviderTest {

  private static final String validSecretKey = "koFq8Vi30wcrLI1/zFlA3EVj5I/zCG8qacoJgd0yOCE=";
  private final JwtProvider jwtProvider = new JwtProvider(validSecretKey);

  private static JwtProvider createRandomKeyProvider() {
    final SecretKey secretKey = SIG.HS256.key().build();
    return new JwtProvider(Encoders.BASE64.encode(secretKey.getEncoded()));
  }

  @Test
  void 정상적으로_토큰을_생성한다() {
    final Long validId = 10L;

    final String token = jwtProvider.createAccessTokenWith(validId);

    final Long actual = jwtProvider.parseMemberId(token);
    assertThat(actual)
        .isEqualTo(validId);
  }

  @Test
  void secretKey가_다르면_Exception이_발생한다() {
    final Long validId = 10L;
    final JwtProvider differentKeyProvider = createRandomKeyProvider();

    final String token = jwtProvider.createAccessTokenWith(validId);

    Assertions.assertThatThrownBy(() -> differentKeyProvider.parseMemberId(token));
  }
}