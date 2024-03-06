package capstone.safeat.oauth.external;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record GoogleAccessToken(
    String issuedAt,
    String scope,
    String applicationName,
    String refreshTokenIssuedAt,
    String status,
    String refreshTokenStatus,
    String apiProductList,
    String expiresIn,
    String organizationId,
    String tokenType,
    String refreshToken,
    String clientId,
    String accessToken,
    String organizationName,
    String refreshTokenExpiresIns,
    String refreshCount
) {

}
