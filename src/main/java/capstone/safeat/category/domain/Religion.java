package capstone.safeat.category.domain;

import static capstone.safeat.category.domain.Category.ASAFOETIDA;
import static capstone.safeat.category.domain.Category.BEEF;
import static capstone.safeat.category.domain.Category.CHIVES;
import static capstone.safeat.category.domain.Category.CRUSTACEANS;
import static capstone.safeat.category.domain.Category.GARLIC;
import static capstone.safeat.category.domain.Category.GREEN_ONION;
import static capstone.safeat.category.domain.Category.HERBAGE_CROP;
import static capstone.safeat.category.domain.Category.MEATS;
import static capstone.safeat.category.domain.Category.OTHER_MOLLUSKS;
import static capstone.safeat.category.domain.Category.PORK;
import static capstone.safeat.category.domain.Category.ROOT_VEGETABLES;
import static capstone.safeat.category.domain.Category.SEASONINGS;
import static capstone.safeat.category.domain.Category.SEA_FOODS;
import static capstone.safeat.category.domain.Category.SHELLFISH;
import static capstone.safeat.category.domain.Category.WILD_CHIVE;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Religion {

  ISLAM(201L, "이슬람교", "Islam",
      List.of(MEATS, PORK, OTHER_MOLLUSKS, SEA_FOODS)
  ),
  JUDAISM(202L, "유대교", "Judaism",
      concatList(
          List.of(PORK, MEATS, CRUSTACEANS, SEA_FOODS, OTHER_MOLLUSKS, SHELLFISH),
          SHELLFISH.getAllChildren(), CRUSTACEANS.getAllChildren()
      )
  ),
  HINDUISM(203L, "힌두교", "Hinduism",
      List.of(MEATS, BEEF)
  ),
  BUDDHISM(204L, "불교", "buddhism",
      concatList(
          List.of(MEATS, CHIVES, HERBAGE_CROP, GARLIC, GREEN_ONION, WILD_CHIVE,
              ROOT_VEGETABLES, ASAFOETIDA, SEASONINGS),
          MEATS.getAllChildren()
      )
  );

  private final Long id;
  private final String koreanName;
  private final String englishName;
  private final List<Category> children;

  Religion(final Long id, final String koreanName, final String englishName,
      final List<Category> children) {
    this.id = id;
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.children = children;
  }

  @SafeVarargs
  public static List<Category> concatList(final List<Category>... categoryLists) {
    return Arrays.stream(categoryLists)
        .flatMap(List::stream)
        .toList();
  }
}
