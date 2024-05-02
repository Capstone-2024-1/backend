package capstone.safeat.group.dto;

import capstone.safeat.group.domain.GroupDomain;
import java.util.List;

public record GroupPreviewResponse(
    Long id,
    String name,
    String imageUrl,
    int peopleCount,
    String creatorName
) {

  public static List<GroupPreviewResponse> convertList(final List<GroupDomain> groups) {
    return groups.stream()
        .map(GroupPreviewResponse::from)
        .toList();
  }

  private static GroupPreviewResponse from(final GroupDomain group) {
    final Long id = group.getId();
    final String name = group.getName();
    final String imageUrl = group.getImageUrl();
    final int peopleCount = group.getMembers().size();
    final String creatorName = group.getCreator().getNickName();

    return new GroupPreviewResponse(id, name, imageUrl, peopleCount, creatorName);
  }
}
