package capstone.safeat.category.application;

import capstone.safeat.category.domain.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

  private final CategoryReader categoryReader;

  @Transactional(readOnly = true)
  public List<Category> findAllCategory() {
    return categoryReader.readAll();
  }
}
