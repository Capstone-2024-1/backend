package capstone.safeat.filter.external;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class NonFoodDataFilterTest {

  @Test
  void 한글을이_아니면_false_를_반환한다() {
    final String validName = "원";
    final String invalidName = "1,000";
    final String invalidName2 = "1,000원";
    final String invalidName3 = "-";

    assertAll(
        () -> assertThat(NonFoodDataFilter.isValidFoodName(validName))
            .isTrue(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName))
            .isFalse(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName2))
            .isFalse(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName3))
            .isFalse()
    );
  }
}
