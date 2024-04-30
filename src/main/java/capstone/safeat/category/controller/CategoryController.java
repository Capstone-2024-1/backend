package capstone.safeat.category.controller;

import capstone.safeat.category.application.CategoryService;
import capstone.safeat.category.dto.CategoryResponse;
import capstone.safeat.category.dto.ReligionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> getCategoryTree() {
    final var categories = categoryService.findAllCategory();
    final List<CategoryResponse> categoryTree = CategoryResponse.convertHierarchy(categories);
    return ResponseEntity.ok(categoryTree);
  }

  @GetMapping("/religions")
  public ResponseEntity<List<ReligionResponse>> getReligionTree() {
    final var religions = categoryService.findAllReligion();
    final List<ReligionResponse> categoryTree = ReligionResponse.convertHierarchy(religions);
    return ResponseEntity.ok(categoryTree);
  }
}
