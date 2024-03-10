package capstone.safeat.member.controller;

import capstone.safeat.member.application.MemberService;
import capstone.safeat.member.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/login/oauth/{oauthType}")
  public ResponseEntity<LoginResponse> login(
      @PathVariable final String oauthType, @RequestParam final String code
  ) {
    final LoginResponse response = memberService.createToken(oauthType, code);
    return ResponseEntity.ok(response);
  }
}
