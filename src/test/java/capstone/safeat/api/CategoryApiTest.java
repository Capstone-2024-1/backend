package capstone.safeat.api;

import static capstone.safeat.fixture.docs.CategoryDocsFixture.카테고리들;
import static java.sql.JDBCType.ARRAY;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.category.domain.Category;
import capstone.safeat.support.ApiTest;
import java.util.List;
import org.junit.jupiter.api.Test;


public class CategoryApiTest extends ApiTest {

  @Test
  void 카테고리_종류를_반환한다() throws Exception {
    final List<Category> categories = 카테고리들();

    when(categoryService.findAllCategory()).thenReturn(categories);

    mockMvc.perform(get("/categories"))
        .andExpect(status().isOk())
        .andDo(document("category tree",
            responseFields(
                fieldWithPath("[]").type(ARRAY).description("카테고리 전체"),
                fieldWithPath("[].id").type(NUMBER).description("멤버의 id"),
                fieldWithPath("[].englishName").type(STRING).description("영어 이름"),
                fieldWithPath("[].koreanName").type(STRING).description("한국 이름"),
                fieldWithPath("[].flatChildIds[]").type(ARRAY).description("자식 카테고리의 flat한 id"),
                subsectionWithPath("[].childCategories[]").type(ARRAY).description("하위 카테고리 목록")
            )
        ));
  }
}
