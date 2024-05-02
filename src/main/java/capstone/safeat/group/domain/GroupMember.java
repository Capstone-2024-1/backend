package capstone.safeat.group.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_member")
@NoArgsConstructor(access = PROTECTED)
public class GroupMember {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Getter
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "group_id", nullable = false
      , foreignKey = @ForeignKey(name = "fk_group_member_to_group"))
  private Group group;

  @Getter
  private Long memberId;

  public GroupMember(final Group group, final Long memberId) {
    this.group = group;
    this.memberId = memberId;
  }
}
