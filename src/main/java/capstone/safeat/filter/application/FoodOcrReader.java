package capstone.safeat.filter.application;

import capstone.safeat.filter.vo.Food;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FoodOcrReader {

  List<Food> readFoods(final MultipartFile menuImage);
}
