package capstone.safeat.filter.domain;

import capstone.safeat.category.domain.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EstimatedFood {

  private final List<Category> categories;
  private final boolean isAmbiguous;
  private final boolean isFood;
}
