package capstone.safeat.fixture.domain;

import capstone.safeat.category.domain.Category;

public class CategoryDomainFixture {

  public static Category 과일() {
    return Category.builder()
        .koreanName("과일")
        .englishName("Fruit")
        .build();
  }

  public static Category 채소() {
    return Category.builder()
        .koreanName("채소")
        .englishName("Vegetables")
        .build();
  }

  public static Category 사과(final Category parent) {
    return Category.builder()
        .koreanName("사과")
        .englishName("Apple")
        .parent(parent)
        .build();
  }

  public static Category 망고(final Category parent) {
    return Category.builder()
        .koreanName("망고")
        .englishName("Mango")
        .parent(parent)
        .build();
  }

  public static Category 뿌리채소(final Category parent) {
    return Category.builder()
        .koreanName("뿌리채소")
        .englishName("Root Vegetables")
        .parent(parent)
        .build();
  }

  public static Category 과일채소(final Category parent) {
    return Category.builder()
        .koreanName("과일채소")
        .englishName("Fruiting Vegetables")
        .parent(parent)
        .build();
  }

  public static Category 감자(final Category parent) {
    return Category.builder()
        .koreanName("감자")
        .englishName("Potato")
        .parent(parent)
        .build();
  }

  public static Category 고구마(final Category parent) {
    return Category.builder()
        .koreanName("고구마")
        .englishName("Sweet Potato")
        .parent(parent)
        .build();
  }

  public static Category 오이(final Category parent) {
    return Category.builder()
        .koreanName("오이")
        .englishName("Cucumber")
        .parent(parent)
        .build();
  }

  public static Category 고추(final Category parent) {
    return Category.builder()
        .koreanName("고추")
        .englishName("Pepper")
        .parent(parent)
        .build();
  }
}
