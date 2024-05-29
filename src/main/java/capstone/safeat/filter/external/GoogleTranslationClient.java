package capstone.safeat.filter.external;

import capstone.safeat.filter.application.TranslationClient;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Profile("prod")
@Component
public class GoogleTranslationClient implements TranslationClient {

  private final Translate translate;

  public GoogleTranslationClient(
      @Value("${google.credentialPath}") final String path
  ) {
    translate = initTranslateIfProd(path);
  }

  @Override
  public String fromKoreanToEnglish(final String korean) {
    final Translation translation = translate.translate(
        korean
        , TranslateOption.sourceLanguage("ko")
        , TranslateOption.targetLanguage("en")
        , TranslateOption.model("base")
    );
    return translation.getTranslatedText();
  }

  private Translate initTranslateIfProd(final String path) {
    try (final InputStream serviceStream = new ClassPathResource(path).getInputStream()) {
      final ServiceAccountCredentials credential = ServiceAccountCredentials
          .fromStream(serviceStream);

      return TranslateOptions.newBuilder()
          .setCredentials(credential)
          .build()
          .getService();
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }
}
