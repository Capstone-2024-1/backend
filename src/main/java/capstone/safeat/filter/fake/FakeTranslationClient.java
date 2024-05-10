package capstone.safeat.filter.fake;

import capstone.safeat.filter.application.TranslationClient;
import org.springframework.stereotype.Component;

@Component
public class FakeTranslationClient implements TranslationClient {

  @Override
  public String fromKoreanToEnglish(final String korean) {
    return "fake English Name" + korean;
  }
}
