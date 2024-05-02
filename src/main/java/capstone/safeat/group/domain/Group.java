package capstone.safeat.group.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Group {

  private static final String DEFAULT_GROUP_IMAGE_URL = "";

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  private String imageUrl;

  @NotNull
  private String name;

  @NotNull
  private Long creatorId;

  @Builder
  private Group(final Long id, final String name, final String imageUrl,
      final Long creatorId) {
    this.id = id;
    this.name = name;
    this.imageUrl = imageUrl;
    this.creatorId = creatorId;
  }

  public static Group create(final String name, final Member creator) {
    return new Group(null, name, DEFAULT_GROUP_IMAGE_URL, creator.getId());
  }
}
