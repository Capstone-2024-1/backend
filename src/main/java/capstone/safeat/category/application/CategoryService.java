package capstone.safeat.category.application;

import capstone.safeat.category.domain.CategoryRefactor;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

  public List<CategoryRefactor> findAllCategory() {
    return Arrays.stream(CategoryRefactor.values()).toList();
  }
}
