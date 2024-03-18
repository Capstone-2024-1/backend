package capstone.safeat.group.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_")
@NoArgsConstructor(access = PROTECTED)
public class Group {

  private static final String DEFAULT_GROUP_IMAGE_URL = "";

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Getter
  private Long id;

  @NotNull
  private String imageUrl;

  @NotNull
  private Long creatorId;

  private Group(final String imageUrl, final Long creatorId) {
    this.imageUrl = imageUrl;
    this.creatorId = creatorId;
  }

  public static Group create(final Member creator) {
    return new Group(DEFAULT_GROUP_IMAGE_URL, creator.getId());
  }
}
