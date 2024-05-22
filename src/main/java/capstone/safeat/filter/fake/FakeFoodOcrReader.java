package capstone.safeat.filter.fake;

import capstone.safeat.filter.application.FoodOcrReader;
import capstone.safeat.filter.vo.Food;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("local")
@Component
public class FakeFoodOcrReader implements FoodOcrReader {

  @Override
  public List<Food> readFoods(final MultipartFile menuImage) {
    return List.of(new Food("김치찌개"));
  }
}
