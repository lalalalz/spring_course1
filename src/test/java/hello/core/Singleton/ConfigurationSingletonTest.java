package hello.core.Singleton;

import hello.core.AppConfig;
import hello.core.Member.MemberRepository;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {


    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("MemberRepository의 싱글톤 적용 확인")
    void MemberRepositorySigletonTest() {

        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
    }

    @Test
    @DisplayName("Deep Configuration")
    void DeepConfigurationTest() {

        AppConfig appConfigBean = ac.getBean(AppConfig.class);

        System.out.println("appConfigBean = " + appConfigBean.getClass());
    }
}
