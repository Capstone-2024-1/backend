package capstone.safeat.support;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import capstone.safeat.base.LocalDateTimeProvider;
import capstone.safeat.category.application.CategoryService;
import capstone.safeat.category.controller.CategoryController;
import capstone.safeat.config.WebConfig;
import capstone.safeat.filter.application.FilterService;
import capstone.safeat.filter.controller.FilterController;
import capstone.safeat.group.application.GroupService;
import capstone.safeat.group.controller.GroupController;
import capstone.safeat.login.application.JwtProvider;
import capstone.safeat.login.application.LoginService;
import capstone.safeat.login.controller.LoginController;
import capstone.safeat.member.application.MemberService;
import capstone.safeat.member.controller.MemberController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest({
    MemberController.class, LoginController.class, CategoryController.class, GroupController.class,
    FilterController.class
})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@MockBean(JpaMetamodelMappingContext.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Import({RestDocsResultConfig.class})
public abstract class ApiTest {

  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected ObjectMapper objectMapper;
  @MockBean
  protected MemberService memberService;
  @MockBean
  protected LoginService loginService;
  @MockBean
  protected CategoryService categoryService;
  @MockBean
  protected GroupService groupService;
  @MockBean
  protected JwtProvider jwtProvider;
  @MockBean
  protected WebConfig webConfig;
  @MockBean
  protected FilterService filterService;

  //  @MockBean
//  protected GroupService groupService;

  @BeforeEach
  void setUp(
      final WebApplicationContext applicationContext,
      final RestDocumentationContextProvider provider
  ) {
    mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
        .apply(documentationConfiguration(provider).operationPreprocessors()
            .withRequestDefaults(prettyPrint())
            .withResponseDefaults(prettyPrint()))
        .build();
  }

  protected void setAccessToken(final String accessToken, final Long id) {
    when(jwtProvider.parseMemberId(accessToken)).thenReturn(id);
  }
}

@TestConfiguration
class RestDocsResultConfig {

  @Bean
  LocalDateTimeProvider localDateTimeProvider() {
    return new LocalDateTimeProvider();
  }
}
