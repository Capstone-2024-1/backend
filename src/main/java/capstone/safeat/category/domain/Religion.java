package capstone.safeat.category.domain;

import static capstone.safeat.category.domain.Category.ASAFOETIDA;
import static capstone.safeat.category.domain.Category.BEEF;
import static capstone.safeat.category.domain.Category.CHIVES;
import static capstone.safeat.category.domain.Category.CRUSTACEANS;
import static capstone.safeat.category.domain.Category.GARLIC;
import static capstone.safeat.category.domain.Category.GREEN_ONION;
import static capstone.safeat.category.domain.Category.MEATS;
import static capstone.safeat.category.domain.Category.OTHER_MOLLUSKS;
import static capstone.safeat.category.domain.Category.PORK;
import static capstone.safeat.category.domain.Category.SHELLFISH;
import static capstone.safeat.category.domain.Category.WILD_CHIVE;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Religion {

  ISLAM(201L, "이슬람교", "Islam",
      List.of(PORK, OTHER_MOLLUSKS)
  ),
  JUDAISM(202L, "유대교", "Judaism",
      concatList(
          List.of(PORK, OTHER_MOLLUSKS),
          SHELLFISH.getAllLeafChildren(), CRUSTACEANS.getAllLeafChildren()
      )
  ),
  HINDUISM(203L, "힌두교", "Hinduism",
      List.of(BEEF)
  ),
  BUDDHISM(204L, "불교", "buddhism",
      concatList(
          List.of(CHIVES, GARLIC, GREEN_ONION, WILD_CHIVE, ASAFOETIDA),
          MEATS.getAllLeafChildren()
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
