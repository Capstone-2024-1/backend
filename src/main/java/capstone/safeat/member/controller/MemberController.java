package capstone.safeat.member.controller;

import capstone.safeat.member.application.MemberService;
import capstone.safeat.member.dto.JwtMemberId;
import capstone.safeat.member.dto.MemberAddCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/categories")
  public ResponseEntity<Void> addCategory(
      final JwtMemberId jwtMemberId, final MemberAddCategoryRequest addProductRequest
  ) {
    memberService.addCategoryIntoMember(jwtMemberId.memberId(), addProductRequest.categoryIds());
    return ResponseEntity.ok().build();
  }
}
