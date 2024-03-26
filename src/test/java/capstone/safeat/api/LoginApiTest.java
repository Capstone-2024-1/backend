package capstone.safeat.api;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.login.dto.LoginRequest;
import capstone.safeat.login.dto.LoginResponse;
import capstone.safeat.support.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class LoginApiTest extends ApiTest {

  @Test
  void 로그인() throws Exception {
    final LoginResponse itemPreviewResponses = new LoginResponse(1L, "access Token", false);
    final String code = "실제 Google OAuth에서 반환해준 Auth 코드";
    final LoginRequest loginRequest = new LoginRequest(code);
    final String requestBody = objectMapper.writeValueAsString(loginRequest);
    final String oauthType = "google";

    when(loginService.createToken(oauthType, code)).thenReturn(itemPreviewResponses);

    mockMvc.perform(post("/login/oauth/google")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(status().isOk())
        .andDo(document("login",
            requestHeaders(
                headerWithName(CONTENT_TYPE).description("application/json")
            ),
            requestFields(
                fieldWithPath("code").type(STRING).description("실제 Google OAuth에서 반환해준 Auth 코드")
            ),
            responseFields(
                fieldWithPath("id").type(NUMBER).description("멤버의 id"),
                fieldWithPath("accessToken").type(STRING).description("accessToken"),
                fieldWithPath("isRegistered").type(BOOLEAN).description("회원가입이 완료된 멤버인지")
            )
        ));
  }
}
