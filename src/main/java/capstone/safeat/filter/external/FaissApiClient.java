package capstone.safeat.filter.external;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface FaissApiClient {

  @GetExchange(url = "https://safeat2.dongwoo.win/search")
  FoodEstimateResponse fetchMemberProfile(@RequestParam final MultiValueMap<String, String> params);
}
