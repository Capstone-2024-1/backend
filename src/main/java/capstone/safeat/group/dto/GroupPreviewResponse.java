package capstone.safeat.group.dto;

import java.util.List;

public record GroupPreviewResponse(
    Long id,
    String name,
    String imageUrl,
    int peopleCount,
    String creatorName
) {

}
