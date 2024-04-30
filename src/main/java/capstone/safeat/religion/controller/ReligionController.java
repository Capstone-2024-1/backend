package capstone.safeat.religion.controller;

import capstone.safeat.religion.application.ReligionService;
import capstone.safeat.religion.dto.ReligionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories/religions")
public class ReligionController {

  private final ReligionService religionService;

  @GetMapping
  public ResponseEntity<List<ReligionResponse>> getReligionTree() {
    final var religions = religionService.findAllReligions();
    final List<ReligionResponse> categoryTree = ReligionResponse.convertHierarchy(religions);
    return ResponseEntity.ok(categoryTree);
  }
}
