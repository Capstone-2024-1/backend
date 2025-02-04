package capstone.safeat.group.controller;

import capstone.safeat.category.dto.CategoryResponse;
import capstone.safeat.group.application.GroupService;
import capstone.safeat.group.dto.GroupCreateRequest;
import capstone.safeat.group.dto.GroupExpelRequest;
import capstone.safeat.group.dto.GroupMemberResponse;
import capstone.safeat.group.dto.GroupPreviewResponse;
import capstone.safeat.member.dto.JwtMemberId;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

  private final GroupService groupService;

  @GetMapping
  public ResponseEntity<List<GroupPreviewResponse>> getGroups(final JwtMemberId jwtMemberId) {
    final var groupPreviewResponses = groupService.findRegisteredGroups(jwtMemberId.memberId());
    return ResponseEntity.ok(groupPreviewResponses);
  }

  @GetMapping("/{groupId}/members")
  public ResponseEntity<List<GroupMemberResponse>> getGroupMembers(
      @PathVariable final Long groupId, final JwtMemberId jwtMemberId
  ) {
    final var members = groupService.findMembersInGroup(jwtMemberId.memberId(), groupId);
    final List<GroupMemberResponse> responses = GroupMemberResponse.from(members);
    return ResponseEntity.ok(responses);
  }

  @PostMapping("/create")
  public ResponseEntity<Void> createGroup(
      @RequestBody final GroupCreateRequest groupCreateRequest, final JwtMemberId jwtMemberId
  ) {
    final Long groupId = groupService
        .createGroup(jwtMemberId.memberId(), groupCreateRequest.groupName());
    return ResponseEntity.created(URI.create("/groups/" + groupId)).build();
  }

  @PostMapping("/{groupId}/register")
  public ResponseEntity<Void> registerGroup(
      @PathVariable final Long groupId, final JwtMemberId jwtMemberId
  ) {
    groupService.registerGroup(groupId, jwtMemberId.memberId());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{groupId}/unregister")
  public ResponseEntity<Void> unregisterGroup(
      @PathVariable final Long groupId, final JwtMemberId jwtMemberId
  ) {
    groupService.unregisterGroup(groupId, jwtMemberId.memberId());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{groupId}/expel")
  public ResponseEntity<Void> expelMember(
      @RequestBody final GroupExpelRequest request, @PathVariable final Long groupId,
      final JwtMemberId jwtMemberId
  ) {
    groupService.expel(groupId, jwtMemberId.memberId(), request.memberId());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{groupId}/remove")
  public ResponseEntity<Void> removeGroup(
      @PathVariable final Long groupId, final JwtMemberId jwtMemberId
  ) {
    groupService.removeGroup(groupId, jwtMemberId.memberId());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{groupId}/categories")
  public ResponseEntity<List<CategoryResponse>> readGroupsCategories(
      @PathVariable final Long groupId, final JwtMemberId jwtMemberId
  ) {
    final var categories = groupService.readGroupsCategories(groupId, jwtMemberId.memberId());
    final List<CategoryResponse> responses = CategoryResponse.generateList(categories);
    return ResponseEntity.ok(responses);
  }
}
