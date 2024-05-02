package capstone.safeat.category.domain;

import static capstone.safeat.category.domain.Category.BARLEY;
import static capstone.safeat.category.domain.Category.BEANS;
import static capstone.safeat.category.domain.Category.CRUSTACEANS;
import static capstone.safeat.category.domain.Category.DAIRIES;
import static capstone.safeat.category.domain.Category.EGGS;
import static capstone.safeat.category.domain.Category.FISH;
import static capstone.safeat.category.domain.Category.GRAIN;
import static capstone.safeat.category.domain.Category.NUTS;
import static capstone.safeat.category.domain.Category.SHELLFISH;
import static capstone.safeat.category.domain.Category.WHEAT;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Allergy {

  CRUSTACEANS_ALLERGY(400L, "갑각류 알레르기", "Crustaceans Allergy",
      concatList(List.of(CRUSTACEANS), CRUSTACEANS.getAllChildren())
  ),
  NUTS_ALLERGY(401L, "견과 알레르기", "Nuts Allergy",
      concatList(List.of(NUTS), NUTS.getAllChildren())
  ),
  EGGS_ALLERGY(402L, "계란 알레르기", "Eggs Allergy", List.of(EGGS)),
  GLUTEN_ALLERGY(403L, "글루텐 알레르기", "Gluten Allergy",
      List.of(WHEAT, BARLEY, GRAIN)
  ),
  FISH_ALLERGY(404L, "생선 알레르기", "Fish Allergy",
      concatList(List.of(FISH), FISH.getAllChildren())
  ),
  MILK_ALLERGY(405L, "우유 알레르기", "Milk Allergy",
      concatList(List.of(DAIRIES), DAIRIES.getAllChildren())
  ),
  SHELLFISH_ALLERGY(406L, "조개 알레르기", "Shellfish Allergy",
      concatList(List.of(SHELLFISH), SHELLFISH.getAllChildren())
  ),
  BEAN_ALLERGY(407L, "콩 알레르기", "Bean Allergy",
      concatList(List.of(BEANS, GRAIN))
  );

  private final Long id;
  private final String koreanName;
  private final String englishName;
  private final List<Category> children;

  Allergy(final Long id, final String koreanName, final String englishName,
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
