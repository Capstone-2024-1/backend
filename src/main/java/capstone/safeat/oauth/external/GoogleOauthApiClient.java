package capstone.safeat.oauth.external;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface GoogleOauthApiClient {

  @PostExchange(url = "https://oauth2.googleapis.com/token", contentType = APPLICATION_FORM_URLENCODED_VALUE)
  GoogleOAuthResponse fetchToken(@RequestParam final MultiValueMap<String, String> params);

  @GetExchange(url = "https://www.googleapis.com/oauth2/v1/userinfo")
  GoogleMemberProfileResponse fetchMemberProfile(
      @RequestHeader(name = AUTHORIZATION) String bearerToken
  );
}
