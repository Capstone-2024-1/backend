package capstone.safeat.login;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AuthorizationExtractorTest {

  @Test
  void Auth헤더값_추출() {
    final HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);

    when(httpServletRequest.getHeader(AUTHORIZATION)).thenReturn("Bearer token");

    final String extracted = AuthorizationExtractor.extract(httpServletRequest).trim();

    assertThat(extracted)
        .isEqualTo("token");
  }
}
