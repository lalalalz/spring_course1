package hello.core.Singleton;

import hello.core.AppConfig;
import hello.core.Member.MemberService;
import hello.core.Singletone.SingletonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonServiceTest {

    @Test
    @DisplayName("싱글톤 클래스 테스트")
    void SingletonTest() {

        //IDE 에러 발생
//        SingletonService singletonService = new SingletonService();

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("싱글톤 테스트 with 스프링 컨테이터")
    void SingletonTestWithSpring() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean(MemberService.class);
        MemberService memberService2 = ac.getBean(MemberService.class);

        // 동일한 인스턴스 인지 확인한다.
        // 만약 동일한 인스턴스라면 싱글톤 패턴이 적용되는 것을 확인할 수 있다.
        assertThat(memberService1).isSameAs(memberService2);
    }
}
