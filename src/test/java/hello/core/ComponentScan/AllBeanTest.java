package hello.core.ComponentScan;

import hello.core.AutoAppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Order.DiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    @DisplayName("해당 타입의 모든 빈을 주입받고, 이를 전략 패턴으로 구현")
    void findAllBean() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        int fixDiscountPolicy = discountService.discount(member, 2000, "fixDiscountPolicy");
        int rateDiscountPolicy = discountService.discount(member, 2000, "rateDiscountPolicy");

        System.out.println("fixDiscountPolicy = " + fixDiscountPolicy);
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);

        Assertions.assertThat(fixDiscountPolicy).isEqualTo(1000);
        Assertions.assertThat(rateDiscountPolicy).isEqualTo(200);
    }


    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
        }

        public int discount(Member member, int price, String discountCode) {

            DiscountPolicy discountPolicy = this.policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
