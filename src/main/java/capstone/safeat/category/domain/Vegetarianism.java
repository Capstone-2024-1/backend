package capstone.safeat.category.domain;

import static capstone.safeat.category.domain.Category.CRUSTACEANS;
import static capstone.safeat.category.domain.Category.DAIRIES;
import static capstone.safeat.category.domain.Category.EGGS;
import static capstone.safeat.category.domain.Category.FISH;
import static capstone.safeat.category.domain.Category.FRUITS;
import static capstone.safeat.category.domain.Category.HONEY;
import static capstone.safeat.category.domain.Category.MEATS;
import static capstone.safeat.category.domain.Category.NUTS;
import static capstone.safeat.category.domain.Category.POULTRY;
import static capstone.safeat.category.domain.Category.SEASONINGS;
import static capstone.safeat.category.domain.Category.SEA_FOODS;
import static capstone.safeat.category.domain.Category.SHELLFISH;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Vegetarianism {

  FRUITARIANISM(300L, "프루테리언", "Fruitarianism",
      removeList(
          Arrays.stream(Category.values()).toList(),
          concatList(List.of(FRUITS, NUTS), FRUITS.getAllChildren(), NUTS.getAllChildren())
      )
  ),
  VEGAN(301L, "비건", "VEGAN",
      concatList(
          List.of(MEATS, EGGS, SEA_FOODS, DAIRIES, SEASONINGS, HONEY),
          MEATS.getAllChildren(), POULTRY.getAllChildren(),
          FISH.getAllChildren(), SEA_FOODS.getAllChildren(), SHELLFISH.getAllChildren(),
          CRUSTACEANS.getAllChildren(), DAIRIES.getAllChildren()
      )
  ),
  LACTO(302L, "락토", "LACTO",
      removeList(
          VEGAN.children,
          concatList(List.of(DAIRIES, SEASONINGS, HONEY), DAIRIES.getAllChildren())
      )
  ),
  OVO(303L, "오보", "OVO",
      removeList(VEGAN.children, List.of(EGGS))
  ),
  LACTO_OVO(304L, "락토오보", "LACTO_OVO",
      removeList(LACTO.children, List.of(EGGS))
  ),
  POLO(305L, "폴로", "POLO",
      removeList(concatList(List.of(MEATS), MEATS.getAllChildren()), List.of(POULTRY))
  ),
  PESCO(306L, "페스코", "PESCO",
      concatList(List.of(MEATS), MEATS.getAllChildren(), POULTRY.getAllChildren())
  );

  private final Long id;
  private final String koreanName;
  private final String englishName;
  private final List<Category> children;

  Vegetarianism(final Long id, final String koreanName, final String englishName,
      final List<Category> children) {
    this.id = id;
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.children = children;
  }

  @SafeVarargs
  private static List<Category> concatList(final List<Category>... categoryLists) {
    return Arrays.stream(categoryLists)
        .flatMap(List::stream)
        .toList();
  }

  private static List<Category> removeList(
      final List<Category> target, final List<Category> removes
  ) {
    return target.stream()
        .filter(item -> !removes.contains(item))
        .toList();
  }
}
