package capstone.safeat.filter.application;

import capstone.safeat.category.application.CategoryReader;
import capstone.safeat.category.domain.Category;
import capstone.safeat.filter.dto.FoodFilterResponse;
import capstone.safeat.filter.vo.EstimatedFood;
import capstone.safeat.group.application.GroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FilterService {

  private final FoodOcrReader foodOcrReader;
  private final CategoryEstimater categoryEstimater;
  private final FoodRecipeInferencer foodRecipeInferencer;
  private final TranslationClient translationClient;
  private final CategoryReader categoryReader;
  private final GroupService groupService;

  @Transactional(readOnly = true)
  public FoodFilterResponse filterSingleFood(final String foodName, final Long memberId) {
    final EstimatedFood estimatedFood = categoryEstimater.estimateFood(foodName);
    final List<Category> filterCategories = categoryReader.readCategoriesByMemberId(memberId);
    if (estimatedFood.isAmbiguous()) {
      return generateFoodFilter(foodRecipeInferencer.inferenceFood(foodName), filterCategories);
    }
    return generateFoodFilter(estimatedFood, filterCategories);
  }

  @Transactional(readOnly = true)
  public List<FoodFilterResponse> filterMultiFoodBy(
      final MultipartFile meuImage, final Long memberId
  ) {
    final List<Category> filterCategories = categoryReader.readCategoriesByMemberId(memberId);
    return filteringMenus(meuImage, filterCategories);
  }

  @Transactional(readOnly = true)
  public List<FoodFilterResponse> filterMultiFoodBy(
      final MultipartFile meuImage, final Long memberId, final Long groupId
  ) {
    final List<Category> filterCategories = groupService.readGroupsCategories(groupId, memberId);
    return filteringMenus(meuImage, filterCategories);
  }

  @Transactional(readOnly = true)
  public FoodFilterResponse generateFoodCategories(final Long memberId, final String foodName) {
    final EstimatedFood estimatedFood = foodRecipeInferencer.inferenceFood(foodName);
    final List<Category> filterCategories = categoryReader.readCategoriesByMemberId(memberId);
    return generateFoodFilter(estimatedFood, filterCategories);
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

  private List<FoodFilterResponse> filteringMenus(final MultipartFile meuImage,
      final List<Category> filterCategories) {
    return foodOcrReader.readFoods(meuImage).stream()
        .map(food -> categoryEstimater.estimateFood(food.name()))
        .filter(EstimatedFood::isFood)
        .map(estimatedFood -> generateFoodFilter(estimatedFood, filterCategories))
        .toList();
  }
}
