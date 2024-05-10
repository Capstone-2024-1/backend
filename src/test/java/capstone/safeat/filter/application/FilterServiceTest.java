package capstone.safeat.filter.application;

import static capstone.safeat.category.domain.Category.MANGO;
import static capstone.safeat.category.domain.Category.OTHER_HERBAGE_CROP;
import static capstone.safeat.category.domain.Category.PEPPER;
import static capstone.safeat.category.domain.Category.RICE;
import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import capstone.safeat.category.dto.CategoryResponse;
import capstone.safeat.filter.domain.EstimatedFood;
import capstone.safeat.filter.dto.FoodFilterResponse;
import capstone.safeat.member.application.MemberUpdater;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class FilterServiceTest extends ServiceTest {

  @Autowired
  private FilterService filterService;

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private MemberUpdater memberUpdater;

  @Test
  void 필터링하는_서비스_구현() {
    //given
    final Member member = memberRepository.save(멤버_홍혁준_생성());
    memberUpdater.saveCategoryIntoMember(member, List.of(PEPPER, MANGO));

    final String foodName = "김치볶음밥";
    final String foodEnglishName = "kimchi fired rice";

    final EstimatedFood estimatedFood = new EstimatedFood(
        foodName, List.of(PEPPER, RICE, OTHER_HERBAGE_CROP), false, true
    );
    when(categoryEstimater.estimateFood(foodName)).thenReturn(estimatedFood);
    when(translationClient.fromKoreanToEnglish(foodName)).thenReturn(foodEnglishName);

    //when
    final FoodFilterResponse actual = filterService.filterSingleFood(foodName, member.getId());
    final FoodFilterResponse expected = new FoodFilterResponse(
        foodName, foodEnglishName, CategoryResponse.generateList(List.of(PEPPER)),
        CategoryResponse.generateList(List.of(RICE, OTHER_HERBAGE_CROP)), false, true, false
    );

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}
