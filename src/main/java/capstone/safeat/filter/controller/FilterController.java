package capstone.safeat.filter.controller;

import capstone.safeat.filter.application.FilterService;
import capstone.safeat.filter.dto.FoodFilterResponse;
import capstone.safeat.member.dto.JwtMemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FilterController {

  private final FilterService filterService;

  @GetMapping("/filter")
  public ResponseEntity<FoodFilterResponse> estimateSingleFood(
      final JwtMemberId jwtMemberId, final String foodName
  ) {
    final FoodFilterResponse response = filterService.filterSingleFood(
        foodName, jwtMemberId.memberId()
    );
    return ResponseEntity.ok(response);
  }
}
