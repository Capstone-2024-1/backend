package capstone.safeat.oauth.external;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record GoogleMemberProfileResponse(
    String id,
    String name,
    String givenName,
    String familyName,
    String picture,
    String local
) {

}
