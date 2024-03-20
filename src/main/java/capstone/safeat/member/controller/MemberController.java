package capstone.safeat.member.controller;

import capstone.safeat.member.application.MemberService;
import capstone.safeat.member.dto.LoginRequest;
import capstone.safeat.member.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/login/oauth/{oauthType}")
  public ResponseEntity<LoginResponse> login(
      @PathVariable final String oauthType, @RequestBody final LoginRequest loginRequest
  ) {
    System.out.println("dd");
    final LoginResponse response = memberService.createToken(oauthType, loginRequest.getCode());
    return ResponseEntity.ok(response);
  }
}
