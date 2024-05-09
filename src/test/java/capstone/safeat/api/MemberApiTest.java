package capstone.safeat.api;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.fixture.docs.MemberFixture;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.dto.MemberAddCategoryRequest;
import capstone.safeat.member.dto.MemberNickNameEditRequest;
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

  @Test
  void 멤버_프로필_조회() throws Exception {
    final Long memberId = 10L;
    final Member member = MemberFixture.멤버_홍혁준_생성();

    when(memberService.findMember(memberId)).thenReturn(member);

    mockMvc.perform(get("/members/" + memberId))
        .andExpect(status().isOk())
        .andDo(document("member-profile",
            responseFields(
                fieldWithPath("nickName").type(STRING).description("멤버의 닉네임"),
                fieldWithPath("profileImageUrl").type(STRING).description("멤버의 profileUrl")
            )
        ));
  }

  @Test
  void 멤버_닉네임_변경() throws Exception {
    final MemberNickNameEditRequest nickNameEditRequest = new MemberNickNameEditRequest("수정할 닉네임");

    final String requestBody = objectMapper.writeValueAsString(nickNameEditRequest);
    final Long memberId = 10L;

    setAccessToken(ACCESS_TOKEN, memberId);

    mockMvc.perform(patch("/members/my/name/edit")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
            .header(AUTHORIZATION, "Bearer " + ACCESS_TOKEN))
        .andExpect(status().isOk())
        .andDo(document("member-name-edit",
            requestHeaders(
                headerWithName(CONTENT_TYPE).description("application/json")
            ),
            requestFields(
                fieldWithPath("nickName").type(STRING).description("수정할 멤버의 닉네임")
            )
        ));
    verify(memberService).editMemberNickName(memberId, nickNameEditRequest.nickName());
  }
}
