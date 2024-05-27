package capstone.safeat.filter.external;

import capstone.safeat.filter.external.dto.FoodEstimateResponse;
import capstone.safeat.filter.external.dto.FoodInferenceResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface AiApiClient {

  @GetExchange(url = "http://192.168.5.99:8000/search")
  FoodEstimateResponse estimate(@RequestParam final MultiValueMap<String, String> params);

  @GetExchange(url = "http://192.168.5.99:8000/inference")
  FoodInferenceResponse inference(@RequestParam final MultiValueMap<String, String> params);
}
