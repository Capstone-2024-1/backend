package capstone.safeat.category.domain;

import static capstone.safeat.category.domain.Category.CRUSTACEANS;
import static capstone.safeat.category.domain.Category.DAIRIES;
import static capstone.safeat.category.domain.Category.EGGS;
import static capstone.safeat.category.domain.Category.FISH;
import static capstone.safeat.category.domain.Category.FRUITING_VEGETABLES;
import static capstone.safeat.category.domain.Category.GRAIN;
import static capstone.safeat.category.domain.Category.HERBAGE_CROP;
import static capstone.safeat.category.domain.Category.MEATS;
import static capstone.safeat.category.domain.Category.POULTRY;
import static capstone.safeat.category.domain.Category.ROOT_VEGETABLES;
import static capstone.safeat.category.domain.Category.SEASONINGS;
import static capstone.safeat.category.domain.Category.SEA_FOODS;
import static capstone.safeat.category.domain.Category.SHELLFISH;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Vegetarianism {

  FRUITARIANISM(300L, "프루테리언", "Fruitarianism",
      concatList(
          ROOT_VEGETABLES.getAllChildren(), FRUITING_VEGETABLES.getAllChildren(),
          HERBAGE_CROP.getAllChildren(), GRAIN.getAllChildren(), MEATS.getAllLeafChildren(),
          POULTRY.getAllLeafChildren(), SEA_FOODS.getAllLeafChildren(), FISH.getAllLeafChildren(),
          CRUSTACEANS.getAllLeafChildren(), SHELLFISH.getAllLeafChildren(),
          SEASONINGS.getAllLeafChildren(), DAIRIES.getAllLeafChildren(), List.of(EGGS)
      )
  ),
  VEGAN(301L, "비건", "VEGAN",
      concatList(
          MEATS.getAllLeafChildren(),
          POULTRY.getAllLeafChildren(), SEA_FOODS.getAllLeafChildren(), FISH.getAllLeafChildren(),
          CRUSTACEANS.getAllLeafChildren(), SHELLFISH.getAllLeafChildren(),
          SEASONINGS.getAllLeafChildren(), DAIRIES.getAllLeafChildren(), List.of(EGGS)
      )
  ),
  LACTO(302L, "락토", "LACTO",
      concatList(
          MEATS.getAllLeafChildren(),
          POULTRY.getAllLeafChildren(), SEA_FOODS.getAllLeafChildren(), FISH.getAllLeafChildren(),
          CRUSTACEANS.getAllLeafChildren(), SHELLFISH.getAllLeafChildren(),
          SEASONINGS.getAllLeafChildren(), List.of(EGGS)
      )
  ),
  OVO(303L, "오보", "OVO",
      concatList(
          MEATS.getAllLeafChildren(),
          POULTRY.getAllLeafChildren(), SEA_FOODS.getAllLeafChildren(), FISH.getAllLeafChildren(),
          CRUSTACEANS.getAllLeafChildren(), SHELLFISH.getAllLeafChildren(),
          SEASONINGS.getAllLeafChildren(), DAIRIES.getAllLeafChildren()
      )
  ),
  LACTO_OVO(304L, "락토오보", "LACTO_OVO",
      concatList(
          MEATS.getAllLeafChildren(),
          POULTRY.getAllLeafChildren(), SEA_FOODS.getAllLeafChildren(), FISH.getAllLeafChildren(),
          CRUSTACEANS.getAllLeafChildren(), SHELLFISH.getAllLeafChildren(),
          SEASONINGS.getAllLeafChildren()
      )
  ),
  POLO(305L, "폴로", "POLO",
      concatList(
          MEATS.getAllLeafChildren()
      )
  ),
  PESCO(306L, "페스코", "PESCO",
      concatList(
          MEATS.getAllLeafChildren(), POULTRY.getAllLeafChildren()
      )
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
