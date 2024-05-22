package capstone.safeat.filter.external;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ocr.google")
public record GoogleOcrConfig(
    String projectId,
    String location,
    String processorId,
    String credentialPath
) {

}
