package capstone.safeat.support;

import capstone.safeat.config.DatabaseClearExtension;
import capstone.safeat.filter.application.CategoryEstimater;
import capstone.safeat.filter.application.FoodOcrReader;
import capstone.safeat.filter.application.TranslationClient;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(DatabaseClearExtension.class)
public abstract class ServiceTest {

  @MockBean
  protected CategoryEstimater categoryEstimater;

  @MockBean
  protected TranslationClient translationClient;

  @MockBean
  protected FoodOcrReader foodOcrReader;
}
