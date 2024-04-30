package capstone.safeat.fixture.docs;

import capstone.safeat.category.domain.Category;
import java.util.List;

public class CategoryDocsFixture {

  public static Category 과일() {
    return Category.builder()
        .id(1L)
        .koreanName("과일")
        .englishName("Fruit")
        .build();
  }

  public static Category 채소() {
    return Category.builder()
        .id(2L)
        .koreanName("채소")
        .englishName("Vegetables")
        .build();
  }

  public static Category 사과(final Category parent) {
    return Category.builder()
        .id(3L)
        .koreanName("사과")
        .englishName("Apple")
        .parent(parent)
        .build();
  }

  public static Category 망고(final Category parent) {
    return Category.builder()
        .id(4L)
        .koreanName("망고")
        .englishName("Mango")
        .parent(parent)
        .build();
  }

  public static Category 뿌리채소(final Category parent) {
    return Category.builder()
        .id(5L)
        .koreanName("뿌리채소")
        .englishName("Root Vegetables")
        .parent(parent)
        .build();
  }

  public static Category 과일채소(final Category parent) {
    return Category.builder()
        .id(6L)
        .koreanName("과일채소")
        .englishName("Fruiting Vegetables")
        .parent(parent)
        .build();
  }

  public static Category 감자(final Category parent) {
    return Category.builder()
        .id(7L)
        .koreanName("감자")
        .englishName("Potato")
        .parent(parent)
        .build();
  }

  public static Category 고구마(final Category parent) {
    return Category.builder()
        .id(8L)
        .koreanName("고구마")
        .englishName("Sweet Potato")
        .parent(parent)
        .build();
  }

  public static Category 오이(final Category parent) {
    return Category.builder()
        .id(9L)
        .koreanName("오이")
        .englishName("Cucumber")
        .parent(parent)
        .build();
  }

  public static Category 고추(final Category parent) {
    return Category.builder()
        .id(10L)
        .koreanName("고추")
        .englishName("Pepper")
        .parent(parent)
        .build();
  }

  public static Category 부추() {
    return Category.builder()
        .id(11L)
        .koreanName("부추")
        .englishName("Chives")
        .build();
  }

  public static Category 돼지고기() {
    return Category.builder()
        .id(12L)
        .koreanName("돼지고기")
        .englishName("Pork")
        .build();
  }

  public static Category 기타_연체류() {
    return Category.builder()
        .id(13L)
        .koreanName("기타 연체류")
        .englishName("Other Mollusks")
        .build();
  }

  public static Category 육류() {
    return Category.builder()
        .id(14L)
        .koreanName("육류")
        .englishName("Meat")
        .build();
  }

  public static Category 생선류() {
    return Category.builder()
        .id(15L)
        .koreanName("생선류")
        .englishName("Fish")
        .build();
  }

  public static List<Category> 카테고리들() {
    final Category 과일 = 과일();
    final Category 채소 = 채소();
    final Category 뿌리채소 = 뿌리채소(채소);
    final Category 과일채소 = 과일채소(채소);
    final Category 사과 = 사과(과일);
    final Category 망고 = 망고(과일);
    final Category 감자 = 감자(뿌리채소);
    final Category 고구마 = 고구마(뿌리채소);
    final Category 오이 = 오이(과일채소);
    final Category 고추 = 고추(과일채소);

    return List.of(과일, 채소, 뿌리채소, 과일채소, 사과, 망고, 감자, 고구마, 오이, 고추);
  }
}
