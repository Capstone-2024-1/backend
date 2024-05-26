package capstone.safeat.filter.external;

public class NonFoodDataFilter {

  private static final String KOREAN_FORMAT_REGEX = "[가-힣 ]+";

  public static boolean isValidFoodName(final String foodName) {
    return containOnlyKorean(foodName) && containNonFoodKeyWord(foodName);
  }

  private static boolean containOnlyKorean(final String foodName) {
    return foodName.matches(KOREAN_FORMAT_REGEX);
  }

  private static boolean containNonFoodKeyWord(final String foodName) {
    return true;
  }
}
