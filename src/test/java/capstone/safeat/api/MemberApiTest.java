package capstone.safeat.api;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.member.dto.LoginResponse;
import capstone.safeat.support.ApiTest;
import org.junit.jupiter.api.Test;

public class MemberApiTest extends ApiTest {

  @Test
  void 로그인() throws Exception {
    final LoginResponse itemPreviewResponses = new LoginResponse(1L, "access Token");
    final String code = "code";
    final String oauthType = "google";

    when(memberService.createToken(oauthType, code)).thenReturn(itemPreviewResponses);

    mockMvc.perform(
            post("/login/oauth/" + oauthType)
                .queryParam("code", code)
        )
        .andExpect(status().isOk())
        .andDo(document("login",
            queryParameters(
                parameterWithName("code").description("발급받은 authorization code")
            ),
            responseFields(
                fieldWithPath("id").type(NUMBER).description("멤버의 id"),
                fieldWithPath("accessToken").type(STRING).description("accessToken")
            )
        ));
  }
}
