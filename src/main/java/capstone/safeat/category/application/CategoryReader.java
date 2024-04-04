package capstone.safeat.category.application;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryReader {

  private final CategoryRepository categoryRepository;

  public List<Category> readAll() {
     return categoryRepository.findAll();
  }
}
