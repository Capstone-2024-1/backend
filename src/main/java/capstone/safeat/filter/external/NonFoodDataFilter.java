package capstone.safeat.filter.external;

import java.util.List;

public class NonFoodDataFilter {

  private static final String KOREAN_FORMAT_REGEX = "[가-힣 ]+";
  private static final List<String> NON_FOOD_DATAS = List.of(
      "만두류", "식사류", "김밥류", "주류", "제주산", "음료", "브라질산", "국내산", "중국산", "미국산", "차림표",
      "쿠폰", "배달", "시간", "오늘의", "공지사항", "이벤트", "할인", "안내", "주문 방법", "전화번호", "주소", "배달", "포장",
      "브레이크 타임", "휴무일", "영업일", "메뉴", "안내", "소개", "이용", "휴무", "예약", "참고", "고객", "서비스", "공지", "문의",
      "환영", "공지", "감사", "할인", "종료", "추가", "임시", "변경", "안내", "이용 방법", "확인", "필수", "포인트", "적립", "사용",
      "모집", "경품", "진행", "공지사항", "이벤트", "테이블", "환불", "교환", "상품", "품절", "접수"
  );

  public static boolean isValidFoodName(final String foodName) {
    return containOnlyKorean(foodName) && notContainNonFoodKeyWord(foodName);
  }

  private static boolean containOnlyKorean(final String foodName) {
    return foodName.matches(KOREAN_FORMAT_REGEX);
  }

  private static boolean notContainNonFoodKeyWord(final String foodName) {
    for (final String nonFoodData : NON_FOOD_DATAS) {
      if (foodName.contains(nonFoodData)) {
        return false;
      }
    }
    return true;
  }
}
