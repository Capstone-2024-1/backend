package capstone.safeat.category.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Category extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String koreanName;

  private String englishName;

  private Long parentCategoryId;

  @Builder
  public Category(
      final Long id, final String koreanName,
      final String englishName, final Long parentCategoryId
  ) {
    this.id = id;
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.parentCategoryId = parentCategoryId;
  }

  public boolean isRootCategory() {
    return parentCategoryId == null;
  }
}
