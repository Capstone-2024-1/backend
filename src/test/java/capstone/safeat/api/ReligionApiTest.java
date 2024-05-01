package capstone.safeat.api;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.category.domain.Religion;
import capstone.safeat.support.ApiTest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ReligionApiTest extends ApiTest {

  @Test
  void 종교_종류를_반환한다() throws Exception {
    final List<Religion> religions = Arrays.stream(Religion.values()).toList();

    when(categoryService.findAllReligion()).thenReturn(religions);

    mockMvc.perform(get("/categories/religions"))
        .andExpect(status().isOk())
        .andDo(document("religion-tree",
            responseFields(
                fieldWithPath("[]").type(ARRAY).description("종교 전체"),
                fieldWithPath("[].id").type(NUMBER).description("종교의 id"),
                fieldWithPath("[].englishName").type(STRING).description("종교의 영어이름"),
                fieldWithPath("[].koreanName").type(STRING).description("종교의 한글이름"),
                fieldWithPath("[].flatChildIds[]").type(ARRAY).description("자식 카테고리의 flat한 id"),
                subsectionWithPath("[].childCategories[]").type(ARRAY).description("하위 카테고리 목록")
            )
        ));
  }
}
