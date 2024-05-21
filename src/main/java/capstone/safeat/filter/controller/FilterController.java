package capstone.safeat.filter.controller;

import capstone.safeat.filter.application.FilterService;
import capstone.safeat.filter.dto.FoodFilterRequest;
import capstone.safeat.filter.dto.FoodFilterResponse;
import capstone.safeat.member.dto.JwtMemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  public ResponseEntity<String> estimateMultiFood(
      @RequestPart final MultipartFile menuImage
  ) {
    return ResponseEntity.ok(menuImage.getName());
  }
}
