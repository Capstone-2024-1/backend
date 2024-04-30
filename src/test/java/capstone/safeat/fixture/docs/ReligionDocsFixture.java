package capstone.safeat.fixture.docs;

import static capstone.safeat.fixture.docs.CategoryDocsFixture.기타_연체류;
import static capstone.safeat.fixture.docs.CategoryDocsFixture.돼지고기;
import static capstone.safeat.fixture.docs.CategoryDocsFixture.생선류;
import static capstone.safeat.fixture.docs.CategoryDocsFixture.육류;

import capstone.safeat.category.domain.Category;
import capstone.safeat.religion.domain.Religion;
import capstone.safeat.religion.domain.ReligionCategory;
import java.util.List;

public class ReligionDocsFixture {

  public static Religion 이슬람교() {
    final Category 돼지고기 = 돼지고기();
    final Category 기타_연체류 = 기타_연체류();

    final List<ReligionCategory> religionCategory = List.of(
        new ReligionCategory(null, 기타_연체류), new ReligionCategory(null, 돼지고기)
    );

    return Religion.builder()
        .id(1L)
        .name("이슬람교")
        .religionCategories(religionCategory)
        .build();
  }

  public static Religion 불교() {
    final Category 육류 = 육류();
    final Category 생선류 = 생선류();

    final List<ReligionCategory> religionCategory = List.of(
        new ReligionCategory(null, 육류), new ReligionCategory(null, 생선류)
    );

    return Religion.builder()
        .id(2L)
        .name("불교")
        .religionCategories(religionCategory)
        .build();
  }

  public static List<Religion> 종교들() {
    return List.of(이슬람교(), 불교());
  }
}
