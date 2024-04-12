package capstone.safeat.api;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.member.dto.MemberAddCategoryRequest;
import capstone.safeat.support.ApiTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class MemberApiTest extends ApiTest {

  @Test
  void 멤버에_카테고리_추가() throws Exception {
    final List<Long> categoryIds = List.of(1L, 2L, 3L);
    final MemberAddCategoryRequest loginRequest = new MemberAddCategoryRequest(categoryIds);
    final String requestBody = objectMapper.writeValueAsString(loginRequest);
    final Long memberId = 10L;
    final String token = "TOKEN TOKEN ACCESS TOKEN";

    setAccessToken(token, memberId);
    doNothing().when(memberService).addCategoryIntoMember(memberId, categoryIds);

    mockMvc.perform(post("/members/categories")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
            .header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk())
        .andDo(document("login",
            requestHeaders(
                headerWithName(CONTENT_TYPE).description("application/json")
            ),
            requestFields(
                fieldWithPath("categoryIds").type(ARRAY).description("추가할 category의 id들")
            )
        ));
  }
}
