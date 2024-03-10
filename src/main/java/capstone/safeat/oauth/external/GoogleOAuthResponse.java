package capstone.safeat.oauth.external;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record GoogleOAuthResponse(
    String accessToken,
    int expiresIn,
    String scope,
    String tokenType,
    String idToken
) {

}
