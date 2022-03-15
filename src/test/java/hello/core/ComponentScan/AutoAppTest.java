package hello.core.ComponentScan;

import hello.core.AutoAppConfig;
import hello.core.Member.MemberRepository;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import hello.core.Member.MemoryMemberRepository;
import hello.core.Order.DiscountPolicy;
import hello.core.Order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;


public class AutoAppTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    @DisplayName("빈 충돌 테스트")
    void CrashedBeanTest() {

    }

    @Test
    @DisplayName("컴포넌트 스캔 테스트")
    void CompnentScanTest() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanName = " + beanDefinitionName + " bean = " + bean.getClass());
        }
    }

    @Test
    @DisplayName("의존관계 자동 주입 테스트")
    void AutowiredTest() {

        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();

        MemberRepository memberRepository2 = orderService.getMemberRepository();
        DiscountPolicy discountPolicy = orderService.getDiscountPolicy();


        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("discountPolicy = " + discountPolicy);

        assertThat(memberRepository1).isInstanceOf(MemoryMemberRepository.class);
        assertThat(memberRepository2).isInstanceOf(MemoryMemberRepository.class);
        assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
    }
}
