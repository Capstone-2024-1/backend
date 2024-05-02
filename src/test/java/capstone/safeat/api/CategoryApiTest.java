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

import capstone.safeat.category.domain.Allergy;
import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.Religion;
import capstone.safeat.category.domain.Vegetarianism;
import capstone.safeat.support.ApiTest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;


public class CategoryApiTest extends ApiTest {

  @Test
  void 카테고리_종류를_반환한다() throws Exception {
    final List<Category> categories =
        Arrays.stream(Category.values()).toList();

    when(categoryService.findAllCategory()).thenReturn(categories);

    mockMvc.perform(get("/categories"))
        .andExpect(status().isOk())
        .andDo(document("category tree",
            responseFields(
                fieldWithPath("[]").type(ARRAY).description("카테고리 전체"),
                fieldWithPath("[].id").type(NUMBER).description("카테고리의 id"),
                fieldWithPath("[].englishName").type(STRING).description("영어 이름"),
                fieldWithPath("[].koreanName").type(STRING).description("한국 이름"),
                fieldWithPath("[].flatChildIds[]").type(ARRAY).description("자식 카테고리의 flat한 id"),
                subsectionWithPath("[].childCategories[]").type(ARRAY).description("하위 카테고리 목록")
            )
        ));
  }

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

  @Test
  void 채식주의_종류를_반환한다() throws Exception {
    final List<Vegetarianism> vegetarian = Arrays.stream(Vegetarianism.values()).toList();

    when(categoryService.findAllVegetarianism()).thenReturn(vegetarian);

    mockMvc.perform(get("/categories/vegetarian"))
        .andExpect(status().isOk())
        .andDo(document("vegetarian-tree",
            responseFields(
                fieldWithPath("[]").type(ARRAY).description("채식주의 전체"),
                fieldWithPath("[].id").type(NUMBER).description("채식주의의 id"),
                fieldWithPath("[].englishName").type(STRING).description("채식주의의 영어이름"),
                fieldWithPath("[].koreanName").type(STRING).description("채식주의의 한글이름"),
                fieldWithPath("[].flatChildIds[]").type(ARRAY).description("자식 카테고리의 flat한 id"),
                subsectionWithPath("[].childCategories[]").type(ARRAY).description("하위 카테고리 목록")
            )
        ));
  }

  @Test
  void 알레르기_종류를_반환한다() throws Exception {
    final List<Allergy> allergies = Arrays.stream(Allergy.values()).toList();

    when(categoryService.findAllAllergies()).thenReturn(allergies);

    mockMvc.perform(get("/categories/allergies"))
        .andExpect(status().isOk())
        .andDo(document("allergy-tree",
            responseFields(
                fieldWithPath("[]").type(ARRAY).description("알레르기 전체"),
                fieldWithPath("[].id").type(NUMBER).description("알레르기의 id"),
                fieldWithPath("[].englishName").type(STRING).description("알레르기의 영어이름"),
                fieldWithPath("[].koreanName").type(STRING).description("알레르기의 한글이름"),
                fieldWithPath("[].flatChildIds[]").type(ARRAY).description("자식 카테고리의 flat한 id"),
                subsectionWithPath("[].childCategories[]").type(ARRAY).description("하위 카테고리 목록")
            )
        ));
  }
}
