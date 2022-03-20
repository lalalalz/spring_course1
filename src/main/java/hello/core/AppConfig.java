package hello.core;

import hello.core.Member.MemberRepository;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import hello.core.Member.MemoryMemberRepository;
import hello.core.Order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memoryMemberRepository() {
        System.out.println("call memoryMemberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy RateDiscountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy FixDiscountPolicy() { return new FixDiscountPolicy(); }

    @Bean
    public MemberService memberService() {
        System.out.println("call memberService");
        return new MemberServiceImpl(memoryMemberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call orderService");
        return new OrderServiceImpl(memoryMemberRepository(),
                                    RateDiscountPolicy());
    }
}
