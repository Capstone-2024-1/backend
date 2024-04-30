package capstone.safeat.religion.application;

import capstone.safeat.religion.domain.Religion;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReligionService {

  private final ReligionReader religionReader;

  public List<Religion> findAllReligions() {
    return null;
  }
}
