package hello.core.Singleton;

import hello.core.AppConfig;
import hello.core.Singletone.StatefulService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

public class StatefulServiceTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("Stateful 서비스 테스트")
    void statefulServiceTest() {

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        statefulService1.order("itemA", 1000);
        statefulService2.order("itemB", 2000);

        // ItemA의 가격이 변경되어 버렸다...
        // 실제 서비스라면 엄청 큰 문제가 발생하는 것이다....
        System.out.println("itemA Price = " + statefulService1.getPrice());
        assertThat(statefulService1.getPrice()).isEqualTo(2000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }
}
