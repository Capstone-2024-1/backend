package capstone.safeat.filter.external;

import capstone.safeat.support.ServiceTest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class FaissCategoryEstimaterTest extends ServiceTest {

  @Autowired
  private FaissCategoryEstimater categoryEstimater;

  @Test
  @Disabled
  void 음식이름으로_카테고리_목록_받기() {
    final ExecutorService executor = Executors.newFixedThreadPool(
        100); // 최대 10개의 스레드를 사용하는 스레드 풀 생성

    // 100번의 요청을 스레드 풀에 제출
    for (int i = 0; i < 100; i++) {
      executor.execute(() -> categoryEstimater.estimateFood("치킨")); // 작업을 스레드 풀에 제출
    }

    // 스레드 풀 종료
    executor.shutdown();
    while (!executor.isTerminated()) {
      // 모든 작업이 완료될 때까지 대기
    }
    System.out.println("모든 작업 완료");
  }
}
