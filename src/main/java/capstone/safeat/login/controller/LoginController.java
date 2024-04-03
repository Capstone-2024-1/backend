package capstone.safeat.login.controller;

import capstone.safeat.login.application.LoginService;
import capstone.safeat.login.dto.LoginRequest;
import capstone.safeat.login.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login/oauth/{oauthType}")
  public ResponseEntity<LoginResponse> login(
      @PathVariable final String oauthType, @RequestBody final LoginRequest loginRequest
  ) {
    final LoginResponse response = loginService.createToken(oauthType, loginRequest.getCode());
    return ResponseEntity.ok(response);
  }
}
