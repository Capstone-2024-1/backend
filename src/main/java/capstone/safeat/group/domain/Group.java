package capstone.safeat.group.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Random;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Group {

  private static final String DEFAULT_GROUP_IMAGE_URL_FORMAT = "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/group/group-default-%d.png";

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
    final String groupImageUrl = String.format(
        DEFAULT_GROUP_IMAGE_URL_FORMAT, new Random().nextInt(8) + 1
    );
    return new Group(null, name, groupImageUrl, creator.getId());
  }
}
