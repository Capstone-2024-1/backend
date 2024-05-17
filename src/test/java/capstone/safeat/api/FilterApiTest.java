package capstone.safeat.api;

import static capstone.safeat.category.domain.Category.OTHER_HERBAGE_CROP;
import static capstone.safeat.category.domain.Category.PEPPER;
import static capstone.safeat.category.domain.Category.RICE;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import capstone.safeat.category.dto.CategoryResponse;
import capstone.safeat.filter.dto.FoodFilterRequest;
import capstone.safeat.filter.dto.FoodFilterResponse;
import capstone.safeat.support.ApiTest;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FilterApiTest extends ApiTest {

  private static final String ACCESS_TOKEN = "TOKEN TOKEN ACCESS TOKEN";

  @Test
  void 음식이름으로_필터링_결과_반환하기() throws Exception {
    final FoodFilterRequest request = new FoodFilterRequest("김치볶음밥");
    final Long memberId = 10L;

    final String requestBody = objectMapper.writeValueAsString(request);
    setAccessToken(ACCESS_TOKEN, memberId);

    final FoodFilterResponse response = new FoodFilterResponse(
        "김치볶음밥", "kimchi fired rice", CategoryResponse.generateList(List.of(PEPPER)),
        CategoryResponse.generateList(List.of(RICE, OTHER_HERBAGE_CROP)), false, true, false
    );

    when(filterService.filterSingleFood(request.foodName(), memberId)).thenReturn(response);

    mockMvc.perform(post("/filter")
            .contentType(APPLICATION_JSON_VALUE)
            .content(requestBody)
            .header(AUTHORIZATION, "Bearer " + ACCESS_TOKEN))
        .andExpect(status().isOk())
        .andDo(document("filter-single",
            requestHeaders(
                headerWithName(CONTENT_TYPE).description("application/json")
            ),
            requestFields(
                fieldWithPath("foodName").type(STRING).description("필터링할 음식 이름")
            ),
            responseFields(
                fieldWithPath("koreanName").type(STRING).description("음식 한국 이름"),
                fieldWithPath("englishName").type(STRING).description("음식 영문 이름"),
                subsectionWithPath("cannotEatCategories").type(ARRAY)
                    .description("음식 재료 중 사용자가 먹을 수 없는 재료들"),
                subsectionWithPath("canEatCategories").type(ARRAY)
                    .description("음식 재료 중 사용자가 먹을 수 있는 재료들"),
                fieldWithPath("canEat").type(BOOLEAN).description("먹을 수 있는지"),
                fieldWithPath("isFood").type(BOOLEAN).description("음식 인지"),
                fieldWithPath("isAmbiguous").type(BOOLEAN).description("추출한 카테고리가 모호한지")
            )
        ));
  }
}
