package capstone.safeat.api;

import static capstone.safeat.fixture.docs.MemberFixture.멤버_3명;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.group.dto.GroupCreateRequest;
import capstone.safeat.group.dto.GroupExpelRequest;
import capstone.safeat.group.dto.GroupPreviewResponse;
import capstone.safeat.support.ApiTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class GroupApiTest extends ApiTest {

  @Test
  void 자신이_속한_그룹_종류를_반환한다() throws Exception {
    final Long memberId = 10L;
    final String token = "TOKEN TOKEN ACCESS TOKEN";
    final List<GroupPreviewResponse> responses = List.of(
        new GroupPreviewResponse(1L, "그룹_1", "imageUrl", 4, "전영은"),
        new GroupPreviewResponse(2L, "그룹_2", "imageUrl", 3, "홍혁준"),
        new GroupPreviewResponse(3L, "그룹_3", "imageUrl", 2, "김동우")
    );

    setAccessToken(token, memberId);
    when(groupService.findRegisteredGroups(memberId)).thenReturn(responses);

    mockMvc.perform(get("/groups")
            .header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk())
        .andDo(document("group-preview",
            responseFields(
                fieldWithPath("[]").type(ARRAY).description("그룹 전체"),
                fieldWithPath("[].id").type(NUMBER).description("group Id"),
                fieldWithPath("[].name").type(STRING).description("group 이름"),
                fieldWithPath("[].imageUrl").type(STRING).description("group ImageUrl"),
                fieldWithPath("[].peopleCount").type(NUMBER).description("group에 속한 멤버 수"),
                fieldWithPath("[].creatorName").type(STRING).description("group을 생성한 사람")
            )
        ));
  }

  @Test
  void 특정_그룹의_멤버를_반환() throws Exception {
    final Long memberId = 10L;
    final Long groupId = 100L;
    final String token = "TOKEN TOKEN ACCESS TOKEN";

    setAccessToken(token, memberId);
    when(groupService.findMembersInGroup(memberId, groupId)).thenReturn(멤버_3명());

    mockMvc.perform(get("/groups/" + groupId + "/members")
            .header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk())
        .andDo(document("group-members",
            responseFields(
                fieldWithPath("[]").type(ARRAY).description("멤버 전체"),
                fieldWithPath("[].id").type(NUMBER).description("멤버 Id"),
                fieldWithPath("[].name").type(STRING).description("멤버 닉네임")
            )
        ));
  }

  @Test
  void 그룹을_생성한다() throws Exception {
    final Long memberId = 10L;
    final String groupName = "그룹이름";
    final String token = "TOKEN TOKEN ACCESS TOKEN";
    final Long groupId = 100L;

    final GroupCreateRequest request = new GroupCreateRequest(groupName);
    final String requestBody = objectMapper.writeValueAsString(request);

    setAccessToken(token, memberId);
    when(groupService.createGroup(memberId, groupName)).thenReturn(groupId);

    mockMvc.perform(post("/groups/create")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
            .header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isCreated())
        .andDo(document("group-create",
            requestFields(
                fieldWithPath("groupName").type(STRING).description("생성할 그룹의 이름")
            )
        ));
  }

  @Test
  void 그룹에_멤버를_추가한다() throws Exception {
    final Long memberId = 10L;
    final String token = "TOKEN TOKEN ACCESS TOKEN";
    final Long groupId = 100L;

    setAccessToken(token, memberId);

    mockMvc.perform(post("/groups/" + groupId + "/register")
            .header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk())
        .andDo(document("group-register"));

    verify(groupService).registerGroup(groupId, memberId);
  }

  @Test
  void 그룹에서_멤버가_탈퇴한다() throws Exception {
    final Long memberId = 10L;
    final String token = "TOKEN TOKEN ACCESS TOKEN";
    final Long groupId = 100L;

    setAccessToken(token, memberId);

    mockMvc.perform(post("/groups/" + groupId + "/unregister")
            .header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk())
        .andDo(document("group-unregister"));

    verify(groupService).unregisterGroup(groupId, memberId);
  }

  @Test
  void 그룹에서_멤버를_추방한다() throws Exception {
    final Long memberId = 10L;
    final String token = "TOKEN TOKEN ACCESS TOKEN";
    final Long groupId = 100L;
    final Long targetMemberId = 15L;

    final GroupExpelRequest request = new GroupExpelRequest(targetMemberId);
    final String requestBody = objectMapper.writeValueAsString(request);

    setAccessToken(token, memberId);

    mockMvc.perform(post("/groups/" + groupId + "/expel")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
            .header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk())
        .andDo(document("group-expel",
            requestFields(
                fieldWithPath("memberId").type(NUMBER).description("추방시킬 멤버의 Id")
            )
        ));

    verify(groupService).expel(groupId, memberId, targetMemberId);
  }

  @Test
  void 그룹을_삭제한다() throws Exception {
    final Long memberId = 10L;
    final String token = "TOKEN TOKEN ACCESS TOKEN";
    final Long groupId = 100L;

    setAccessToken(token, memberId);

    mockMvc.perform(post("/groups/" + groupId + "/remove")
            .header(AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isOk())
        .andDo(document("group-remove"));

    verify(groupService).removeGroup(groupId, memberId);
  }
}
