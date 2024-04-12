package capstone.safeat.category.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Optional;
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

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> children;

  @Builder
  public Category(
      final Long id, final String koreanName,
      final String englishName, final Category parent
  ) {
    this.id = id;
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.parent = parent;
  }

  public boolean isRootCategory() {
    return parent == null;
  }

  public Optional<Category> getParent() {
    return Optional.ofNullable(parent);
  }
}
