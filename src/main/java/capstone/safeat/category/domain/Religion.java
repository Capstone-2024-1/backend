package capstone.safeat.category.domain;

import java.util.List;
import lombok.Getter;

@Getter
public enum Religion {

  CHRISTIAN(200L, "기독교", "Christian", List.of()),
  ISLAM(201L, "이슬람교", "Islam", List.of()),
  JUDAISM(202L, "유대교", "Judaism", List.of()),
  HINDUISM(203L, "힌두교", "Hinduism", List.of()),
  BUDDHISM(204L, "불교", "buddhism", List.of());

  private final Long id;
  private final String koreanName;
  private final String englishName;
  private final List<Category> children;

  Religion(
      final Long id, final String koreanName, final String englishName,
      final List<Category> children
  ) {
    this.id = id;
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.children = children;
  }
}
