package capstone.safeat.group.domain;

import static capstone.safeat.group.exception.GroupExceptionType.EXECUTORS_IS_NOT_CREATOR;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.group.exception.GroupException;
import capstone.safeat.member.domain.Member;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_")
@NoArgsConstructor(access = PROTECTED)
public class Group {

  private static final String DEFAULT_GROUP_IMAGE_URL = "";

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  private String imageUrl;

  @Embedded
  private GroupMembers groupMembers;

  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "creator_id", nullable = false
      , foreignKey = @ForeignKey(name = "fk_group_to_member"))
  private Member creator;

  private Group(
      final String imageUrl, final List<GroupMember> groupMembers, final Member creator
  ) {
    this.imageUrl = imageUrl;
    this.groupMembers = new GroupMembers(groupMembers);
    this.creator = creator;
  }

  public Group(final Member creator) {
    this(DEFAULT_GROUP_IMAGE_URL, new ArrayList<>(), creator);
    addMember(creator);
  }

  public void addMember(final Member member) {
    groupMembers.addMember(new GroupMember(this, member));
  }

  public void drop(final Member member) {
    groupMembers.remove(member);
  }

  public void expel(final Member creator, final Member member) {
    if (!this.creator.equals(creator)) {
      throw new GroupException(EXECUTORS_IS_NOT_CREATOR);
    }
    drop(member);
  }
}
