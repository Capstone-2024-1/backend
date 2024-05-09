package capstone.safeat.category.dto;

import static capstone.safeat.category.domain.Allergy.BEAN_ALLERGY;
import static capstone.safeat.category.domain.Allergy.MILK_ALLERGY;
import static capstone.safeat.category.domain.Category.BEANS;
import static capstone.safeat.category.domain.Category.BUTTER;
import static capstone.safeat.category.domain.Category.CHEESE;
import static capstone.safeat.category.domain.Category.DAIRIES;
import static capstone.safeat.category.domain.Category.GRAIN;
import static capstone.safeat.category.domain.Category.MILK;
import static capstone.safeat.category.domain.Category.OTHER_DAIRY_PRODUCTS;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Allergy;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AllergyResponseTest {

  @Test
  void 알러지_반환값들을_정상적으로_생성한다() {
    //given
    final List<Allergy> allergies = List.of(MILK_ALLERGY, BEAN_ALLERGY);
    final List<AllergyResponse> expected = List.of(
        new AllergyResponse(MILK_ALLERGY.getId(), MILK_ALLERGY.getEnglishName(),
            MILK_ALLERGY.getKoreanName(), Set.of(9L, 79L, 80L, 81L, 82L),
            List.of(
                new CategoryResponse(9L, DAIRIES.getEnglishName(), DAIRIES.getKoreanName(),
                    Set.of(79L, 80L, 81L, 82L),
                    List.of(
                        CategoryResponse.fromWithEmptyChildren(MILK),
                        CategoryResponse.fromWithEmptyChildren(CHEESE),
                        CategoryResponse.fromWithEmptyChildren(BUTTER),
                        CategoryResponse.fromWithEmptyChildren(OTHER_DAIRY_PRODUCTS)
                    )
                )
            )
        ),
        new AllergyResponse(BEAN_ALLERGY.getId(), BEAN_ALLERGY.getEnglishName(),
            BEAN_ALLERGY.getKoreanName(), Set.of(41L, 46L),
            List.of(
                new CategoryResponse(GRAIN.getId(), GRAIN.getEnglishName(), GRAIN.getKoreanName(),
                    Set.of(BEANS.getId()),
                    List.of(CategoryResponse.fromWithEmptyChildren(BEANS))
                )
            )
        )
    );

    //when
    final List<AllergyResponse> allergyResponses = AllergyResponse.convertHierarchy(allergies);

    //then
    assertThat(allergyResponses)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}