package capstone.safeat.filter.application;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.filter.domain.EstimatedFood;
import capstone.safeat.filter.dto.FoodFilterResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterService {

  private final CategoryReader categoryReader;
  private final CategoryEstimater categoryEstimater;
  private final TranslationClient translationClient;

  public FoodFilterResponse filterSingleFood(final String foodName, final Long memberId) {
    final EstimatedFood estimatedFood = categoryEstimater.estimateFood(foodName);
    final List<Category> filterCategories = categoryReader.readCategoriesByMemberId(memberId);
    return FoodFilterResponse.of(
        estimatedFood,
        translationClient.fromKoreanToEnglish(estimatedFood.getKoreanName()),
        estimatedFood.extractCannotEatCategories(filterCategories),
        estimatedFood.extractCanEatCategories(filterCategories)
    );
  }
}
