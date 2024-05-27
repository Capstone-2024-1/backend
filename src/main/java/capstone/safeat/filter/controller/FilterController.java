package capstone.safeat.filter.controller;

import capstone.safeat.filter.application.FilterService;
import capstone.safeat.filter.dto.FoodFilterRequest;
import capstone.safeat.filter.dto.FoodFilterResponse;
import capstone.safeat.member.dto.JwtMemberId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FilterController {

  private final FilterService filterService;

  @PostMapping("/filter")
  public ResponseEntity<FoodFilterResponse> estimateSingleFood(
      final JwtMemberId jwtMemberId, @RequestBody final FoodFilterRequest request
  ) {
    final FoodFilterResponse response = filterService.filterSingleFood(
        request.foodName(), jwtMemberId.memberId()
    );
    return ResponseEntity.ok(response);
  }

  @PostMapping(path = "/menu", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<List<FoodFilterResponse>> generateMenuBoardForSingle(
      @RequestPart final MultipartFile menuImage, final JwtMemberId jwtMemberId
  ) {
    final List<FoodFilterResponse> foodFilterResponses
        = filterService.filterMultiFoodBy(menuImage, jwtMemberId.memberId());
    return ResponseEntity.ok(foodFilterResponses);
  }

  @PostMapping(path = "/menu/groups/{groupId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<List<FoodFilterResponse>> generateMenuBoardForGroup(
      @RequestPart final MultipartFile menuImage, final JwtMemberId jwtMemberId,
      @PathVariable final Long groupId
  ) {
    final List<FoodFilterResponse> foodFilterResponses
        = filterService.filterMultiFoodBy(menuImage, jwtMemberId.memberId(), groupId);
    return ResponseEntity.ok(foodFilterResponses);
  }

  @PostMapping(path = "/menu/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<List<FoodFilterResponse>> estimateMultiFoodTest(
      @RequestPart final MultipartFile menuImage
  ) {
    final List<FoodFilterResponse> foodFilterResponses
        = filterService.filterMultiFoodBy(menuImage, 2L);
    return ResponseEntity.ok(foodFilterResponses);
  }

  @PostMapping(path = "/menu/generate")
  public ResponseEntity<FoodFilterResponse> generateMenuBoardForSingle(
      @RequestBody final FoodFilterRequest request, final JwtMemberId jwtMemberId
  ) {
    final FoodFilterResponse response = filterService
        .generateFoodCategories(jwtMemberId.memberId(), request.foodName());
    return ResponseEntity.ok(response);
  }
}
