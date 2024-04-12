package capstone.safeat.member.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.base.BaseEntity;
import capstone.safeat.category.domain.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class MemberCategory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private Long memberId;

  @ManyToOne(fetch = LAZY)
  private Category category;

  public MemberCategory(final Long memberId, final Category category) {
    this.memberId = memberId;
    this.category = category;
  }
}
