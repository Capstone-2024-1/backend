package capstone.safeat.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginResponse {

  private final String accessToken;

  private LoginResponse() {
    this(null);
  }
}
