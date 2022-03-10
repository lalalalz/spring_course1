package hello.core.FindBean;

import hello.core.AppConfig;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import hello.core.Order.DiscountPolicy;
import hello.core.Order.RateDiscountOrderServiceTest;
import hello.core.Order.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class FindApplicationContextBeanBasic {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("타입, 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanByConcreteType() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("동일한 타입의 빈이 여러개 이상일 경우 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
//        DiscountPolicy discountPolicy = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("동일한 타입의 빈이 여러개일 경우에는 이름을 지정하여 문제를 해결하자.")
    void findBeanByNameWhenTypeDuplicate() {
        DiscountPolicy discountPolicy = ac.getBean("RateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회하기")
    void findAllBeansByType() {

        Map<String, DiscountPolicy> discountPolicies = ac.getBeansOfType(DiscountPolicy.class);

        for (String key : discountPolicies.keySet()) {
            System.out.println("key: " + key + "value: " + discountPolicies.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 이름으로 조회하면 된다.")
    void findBeanByNameWhenTypeDuplicateAndInheritance() {
        DiscountPolicy discountPolicy = ac.getBean("RateDiscountPolicy", DiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {

        Map<String, DiscountPolicy> discountPolices = ac.getBeansOfType(DiscountPolicy.class);

        for (String key : discountPolices.keySet()) {
            DiscountPolicy discountPolicy = ac.getBean(key, discountPolices.get(key).getClass());
            System.out.println("name = " + key + "bean = " + discountPolicy);
        }
    }

    @Test
    @DisplayName("Object 타입으로 모두 조회하기")
    void findAllBeansByObjectType() {

        Map<String, Object> discountPolices = ac.getBeansOfType(Object.class);

        for (String key : discountPolices.keySet()) {
            Object bean = ac.getBean(key, discountPolices.get(key).getClass());
            System.out.println("name = " + key + " bean = " + bean);
        }
    }
}
