package capstone.safeat.filter.external;

import static capstone.safeat.filter.exception.FilterExceptionType.GOOGLE_OCR_NOT_AUTHENTICATE;

import capstone.safeat.filter.application.FoodOcrReader;
import capstone.safeat.filter.exception.FilterException;
import capstone.safeat.filter.exception.FilterExceptionType;
import capstone.safeat.filter.vo.Food;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.documentai.v1.Document;
import com.google.cloud.documentai.v1.Document.Page;
import com.google.cloud.documentai.v1.Document.Page.Paragraph;
import com.google.cloud.documentai.v1.Document.TextAnchor;
import com.google.cloud.documentai.v1.DocumentProcessorServiceClient;
import com.google.cloud.documentai.v1.DocumentProcessorServiceSettings;
import com.google.cloud.documentai.v1.ProcessRequest;
import com.google.cloud.documentai.v1.ProcessResponse;
import com.google.cloud.documentai.v1.RawDocument;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Profile("!local")
@Component
public class GoogleOcrReader implements FoodOcrReader {

  private static final String CREDENTIAL_SCOPE = "https://www.googleapis.com/auth/cloud-platform";
  private static final String ENDPOINT_NAME_FORMAT = "projects/%s/locations/%s/processors/%s";
  private static final String ENDPOINT_FORMAT = "%s-documentai.googleapis.com:443";

  private final GoogleOcrConfig googleOcrConfig;
  private final DocumentProcessorServiceSettings documentProcessorServiceSettings;

  public GoogleOcrReader(
      @Value("${google.credentialPath:not prod}") final String path,
      final GoogleOcrConfig googleOcrConfig
  ) {
    this.googleOcrConfig = googleOcrConfig;
    this.documentProcessorServiceSettings
        = createDocumentProcessorSettings(path);
  }

  private DocumentProcessorServiceSettings createDocumentProcessorSettings(
      final String credentialPath
  ) {
    try {
      final String endpoint = String.format(ENDPOINT_FORMAT, googleOcrConfig.location());
      final GoogleCredentials credentials = GoogleCredentials
          .fromStream(new ClassPathResource(credentialPath).getInputStream())
          .createScoped(List.of(CREDENTIAL_SCOPE));
      return DocumentProcessorServiceSettings.newBuilder()
          .setEndpoint(endpoint)
          .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
          .build();
    } catch (final Exception e) {
      log.error("error Message : {}", e.getMessage());
      throw new FilterException(GOOGLE_OCR_NOT_AUTHENTICATE);
    }
  }

  @Override
  public List<Food> readFoods(final MultipartFile menuImage) {
    try (final DocumentProcessorServiceClient client
        = DocumentProcessorServiceClient.create(documentProcessorServiceSettings)
    ) {
      final ProcessRequest request = createRequest(menuImage);
      final ProcessResponse result = client.processDocument(request);

      final Document documentResponse = result.getDocument();

      final String text = documentResponse.getText();

      final Page firstPage = documentResponse.getPages(0);
      final List<Paragraph> paragraphs = firstPage.getParagraphsList();

      return paragraphs.stream()
          .map(paragraph -> getText(paragraph.getLayout().getTextAnchor(), text))
          .map(Food::new)
          .toList();
    } catch (final Exception e) {
      log.error("error Message : {}", e.getMessage());
      throw new FilterException(FilterExceptionType.GOOGLE_OCR_FILTER_FAIL);
    }
  }

  private ProcessRequest createRequest(final MultipartFile menuImage) throws IOException {
    final String name = String.format(ENDPOINT_NAME_FORMAT,
        googleOcrConfig.projectId(), googleOcrConfig.location(), googleOcrConfig.processorId()
    );

    final byte[] fileContent = menuImage.getBytes();
    final ByteString content = ByteString.copyFrom(fileContent);

    final RawDocument document = RawDocument.newBuilder()
        .setContent(content)
        .setMimeType("image/png")
        .build();

    return ProcessRequest.newBuilder()
        .setName(name)
        .setRawDocument(document)
        .build();
  }

  private static String getText(final TextAnchor textAnchor, String text) {
    if (!textAnchor.getTextSegmentsList().isEmpty()) {
      int startIdx = (int) textAnchor.getTextSegments(0).getStartIndex();
      int endIdx = (int) textAnchor.getTextSegments(0).getEndIndex();
      return text.substring(startIdx, endIdx).replaceAll("\\n$", "");
    }
    return "[NO TEXT]";
  }
}