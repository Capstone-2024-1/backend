package capstone.safeat.member.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.group.domain.GroupEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_member")
@NoArgsConstructor(access = PROTECTED)
public class GroupMember {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "group_id", nullable = false
      , foreignKey = @ForeignKey(name = "fk_group_member_to_group"))
  private GroupEntity group;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false
      , foreignKey = @ForeignKey(name = "fk_group_member_to_member"))
  private Member member;
}
