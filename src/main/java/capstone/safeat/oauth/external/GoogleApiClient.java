package capstone.safeat.oauth.external;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;

public interface GoogleApiClient {

  @PostExchange(url = "https://apitest.acme.com/oauth/token", contentType = APPLICATION_FORM_URLENCODED_VALUE)
  GoogleAccessToken fetchToken(@RequestParam final MultiValueMap<String, String> params);
}
