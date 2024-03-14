package capstone.safeat.group.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.member.domain.GroupMemberEntity;
import capstone.safeat.member.domain.MemberEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group")
@NoArgsConstructor(access = PROTECTED)
public class GroupEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String imageUrl;

  @OneToMany(mappedBy = "group", cascade = PERSIST, orphanRemoval = true)
  private List<GroupMemberEntity> groupMember;

  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "creator_id", foreignKey = @ForeignKey(name = "fk_group_to_member"), nullable = false)
  private MemberEntity creator;
}
