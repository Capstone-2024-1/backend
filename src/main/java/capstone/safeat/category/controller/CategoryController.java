package capstone.safeat.category.controller;

import capstone.safeat.category.application.CategoryService;
import capstone.safeat.category.dto.AllergyResponse;
import capstone.safeat.category.dto.CategoryResponse;
import capstone.safeat.category.dto.ReligionResponse;
import capstone.safeat.category.dto.VegetarianismResponse;
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
    final List<CategoryResponse> categoryTree = CategoryResponse.convertHierarchyWithAll(categories);
    return ResponseEntity.ok(categoryTree);
  }

  @GetMapping("/religions")
  public ResponseEntity<List<ReligionResponse>> getReligionTree() {
    final var religions = categoryService.findAllReligion();
    final List<ReligionResponse> religionTree = ReligionResponse.convertHierarchy(religions);
    return ResponseEntity.ok(religionTree);
  }

  @GetMapping("/vegetarian")
  public ResponseEntity<List<VegetarianismResponse>> getVegetarianismTree() {
    final var vegetarian = categoryService.findAllVegetarianism();
    final List<VegetarianismResponse> vegetarianismTree
        = VegetarianismResponse.convertHierarchy(vegetarian);
    return ResponseEntity.ok(vegetarianismTree);
  }

  @GetMapping("/allergies")
  public ResponseEntity<List<AllergyResponse>> getAllergyTree() {
    final var allergies = categoryService.findAllAllergies();
    final List<AllergyResponse> allergyTree
        = AllergyResponse.convertHierarchy(allergies);
    return ResponseEntity.ok(allergyTree);
  }
}
