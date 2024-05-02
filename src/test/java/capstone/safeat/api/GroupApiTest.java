package capstone.safeat.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.group.dto.GroupPreviewResponse;
import capstone.safeat.support.ApiTest;
import java.util.List;
import org.junit.jupiter.api.Test;

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
    when(groupService.findParticipatedGroups(any())).thenReturn(responses);

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
}
