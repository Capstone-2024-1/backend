package capstone.safeat.filter.controller;

import capstone.safeat.filter.application.FilterService;
import capstone.safeat.filter.dto.FoodFilterRequest;
import capstone.safeat.filter.dto.FoodFilterResponse;
import capstone.safeat.member.dto.JwtMemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
