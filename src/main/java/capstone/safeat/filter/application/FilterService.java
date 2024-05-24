package capstone.safeat.filter.application;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.filter.domain.EstimatedFood;
import capstone.safeat.filter.dto.FoodFilterResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FilterService {

  private final FoodOcrReader foodOcrReader;
  private final CategoryReader categoryReader;
  private final CategoryEstimater categoryEstimater;
  private final TranslationClient translationClient;

  @Transactional(readOnly = true)
  public FoodFilterResponse filterSingleFood(final String foodName, final Long memberId) {
    final EstimatedFood estimatedFood = categoryEstimater.estimateFood(foodName);
    final List<Category> filterCategories = categoryReader.readCategoriesByMemberId(memberId);
    return generateFoodFilter(estimatedFood, filterCategories);
  }

  @Transactional(readOnly = true)
  public List<FoodFilterResponse> filterMultiFoodBy(
      final MultipartFile meuImage, final Long memberId
  ) {
    final List<Category> filterCategories = categoryReader.readCategoriesByMemberId(memberId);
    return foodOcrReader.readFoods(meuImage).stream()
        .map(food -> categoryEstimater.estimateFood(food.name()))
        .filter(EstimatedFood::isFood)
        .map(estimatedFood -> generateFoodFilter(estimatedFood, filterCategories))
        .toList();
  }

  private FoodFilterResponse generateFoodFilter(
      final EstimatedFood estimatedFood, final List<Category> filterCategories
  ) {
    return FoodFilterResponse.of(
        estimatedFood,
        translationClient.fromKoreanToEnglish(estimatedFood.getKoreanName()),
        estimatedFood.extractCannotEatCategories(filterCategories),
        estimatedFood.extractCanEatCategories(filterCategories)
    );
  }
}
