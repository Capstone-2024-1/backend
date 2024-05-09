package capstone.safeat.api;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.member.dto.MemberAddCategoryRequest;
import capstone.safeat.member.dto.RegisterRequest;
import capstone.safeat.support.ApiTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class MemberApiTest extends ApiTest {

  private static final String ACCESS_TOKEN = "TOKEN TOKEN ACCESS TOKEN";

  @Test
  void 멤버에_카테고리_추가() throws Exception {
    final List<Long> categoryIds = List.of(1L, 2L, 3L);
    final MemberAddCategoryRequest loginRequest = new MemberAddCategoryRequest(categoryIds);
    final String requestBody = objectMapper.writeValueAsString(loginRequest);
    final Long memberId = 10L;

    setAccessToken(ACCESS_TOKEN, memberId);
    doNothing().when(memberService).addCategoryIntoMember(memberId, categoryIds);

    mockMvc.perform(post("/members/categories")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
            .header(AUTHORIZATION, "Bearer " + ACCESS_TOKEN))
        .andExpect(status().isOk())
        .andDo(document("member-add-category",
            requestHeaders(
                headerWithName(CONTENT_TYPE).description("application/json")
            ),
            requestFields(
                fieldWithPath("categoryIds").type(ARRAY).description("추가할 category의 id들")
            )
        ));
  }

  @Test
  void 멤버_회원가입() throws Exception {
    final List<Long> categoryIds = List.of(1L, 2L, 3L);
    final RegisterRequest registerRequest = new RegisterRequest("닉네임", categoryIds);

    final String requestBody = objectMapper.writeValueAsString(registerRequest);
    final Long memberId = 10L;

    setAccessToken(ACCESS_TOKEN, memberId);

    mockMvc.perform(post("/members/register")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
            .header(AUTHORIZATION, "Bearer " + ACCESS_TOKEN))
        .andExpect(status().isOk())
        .andDo(document("member-register",
            requestHeaders(
                headerWithName(CONTENT_TYPE).description("application/json")
            ),
            requestFields(
                fieldWithPath("categoryIds").type(ARRAY).description("추가할 category의 id들"),
                fieldWithPath("nickName").type(STRING).description("회원가입할 멤버의 닉네임")
            )
        ));
    verify(memberService).register(memberId, categoryIds, registerRequest.nickName());
  }
}
