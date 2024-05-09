package capstone.safeat.filter.config;

import capstone.safeat.filter.external.FaissApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class FilterHttpInterfaceConfig {

  @Bean
  public FaissApiClient faissApiClient() {
    return createHttpInterface(FaissApiClient.class);
  }

  private <T> T createHttpInterface(final Class<T> clazz) {
    final WebClient webClient = WebClient.create();
    final HttpServiceProxyFactory build = HttpServiceProxyFactory
        .builder(WebClientAdapter.forClient(webClient))
        .build();
    return build.createClient(clazz);
  }
}
