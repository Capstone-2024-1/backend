package capstone.safeat.oauth.config;

import capstone.safeat.oauth.external.GoogleOauthApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class OAuthHttpInterfaceConfig {

  @Bean
  public GoogleOauthApiClient googleApiClient() {
    return createHttpInterface(GoogleOauthApiClient.class);
  }

  private <T> T createHttpInterface(final Class<T> clazz) {
    final WebClient webClient = WebClient.create();
    final HttpServiceProxyFactory build = HttpServiceProxyFactory
        .builder(WebClientAdapter.forClient(webClient))
        .build();
    return build.createClient(clazz);
  }
}