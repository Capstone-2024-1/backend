package capstone.safeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SafeatApplication {

  public static void main(String[] args) {
    SpringApplication.run(SafeatApplication.class, args);
  }

}
