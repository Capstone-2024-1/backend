package capstone.safeat.category.application;

import capstone.safeat.category.domain.Allergy;
import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.Religion;
import capstone.safeat.category.domain.Vegetarianism;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

  public List<Category> findAllCategory() {
    return Arrays.stream(Category.values()).toList();
  }

  public List<Religion> findAllReligion() {
    return Arrays.stream(Religion.values()).toList();
  }

  public List<Vegetarianism> findAllVegetarianism() {
    return Arrays.stream(Vegetarianism.values()).toList();
  }

  public List<Allergy> findAllAllergies() {
    return Arrays.stream(Allergy.values()).toList();
  }
}
