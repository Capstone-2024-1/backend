package capstone.safeat.filter.external;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface FaissApiClient {

  @GetExchange(url = "http://192.168.5.99/search")
  FoodEstimateResponse estimate(@RequestParam final MultiValueMap<String, String> params);

  @GetExchange(url = "http://192.168.5.99/inference")
  FoodEstimateResponse inference(@RequestParam final MultiValueMap<String, String> params);
}
