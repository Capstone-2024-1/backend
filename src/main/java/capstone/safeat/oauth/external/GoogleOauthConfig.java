package capstone.safeat.oauth.external;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.google")
public record GoogleOauthConfig(
    String redirectUri,
    String clientId,
    String clientSecret
) {

}
