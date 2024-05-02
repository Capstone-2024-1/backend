package capstone.safeat.group.controller;

import capstone.safeat.group.application.GroupService;
import capstone.safeat.group.dto.GroupPreviewResponse;
import capstone.safeat.member.dto.JwtMemberId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

  private final GroupService groupService;

  @GetMapping
  public ResponseEntity<List<GroupPreviewResponse>> getMembers(final JwtMemberId jwtMemberId) {
    final var groups = groupService.findParticipatedGroups(jwtMemberId);
    final List<GroupPreviewResponse> groupPreviewResponses = GroupPreviewResponse
        .convertList(groups);
    return ResponseEntity.ok(groupPreviewResponses);
  }
}
