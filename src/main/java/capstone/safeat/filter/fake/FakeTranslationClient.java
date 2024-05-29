package capstone.safeat.filter.fake;

import capstone.safeat.filter.application.TranslationClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("local")
@Component
public class FakeTranslationClient implements TranslationClient {

  @Override
  public String fromKoreanToEnglish(final String korean) {
    return "fake English Name" + korean;
  }
}
