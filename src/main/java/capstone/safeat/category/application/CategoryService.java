package capstone.safeat.category.application;

import capstone.safeat.category.domain.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

  private final CategoryReader categoryReader;

  public List<Category> findAllCategory() {
    return categoryReader.readAll();
  }
}
