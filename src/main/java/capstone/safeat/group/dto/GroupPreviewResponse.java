package capstone.safeat.group.dto;

import capstone.safeat.group.domain.Group;

public record GroupPreviewResponse(
    Long id,
    String name,
    String imageUrl,
    int peopleCount,
    String creatorName
) {

  public static GroupPreviewResponse of(
      final Group group, final int peopleCount, final String creatorName
  ) {
    return new GroupPreviewResponse(
        group.getId(),
        group.getName(),
        group.getImageUrl(),
        peopleCount,
        creatorName
    );
  }
}
