package capstone.safeat.filter.config;

import capstone.safeat.filter.external.AiApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class FilterHttpInterfaceConfig {

  @Bean
  public AiApiClient faissApiClient() {
    return createHttpInterface(AiApiClient.class);
  }

  private <T> T createHttpInterface(final Class<T> clazz) {
    final WebClient webClient = WebClient.create();
    final HttpServiceProxyFactory build = HttpServiceProxyFactory
        .builder(WebClientAdapter.forClient(webClient))
        .build();
    return build.createClient(clazz);
  }
}
