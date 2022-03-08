package hello.core;

import hello.core.Member.MemberRepository;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import hello.core.Member.MemoryMemberRepository;
import hello.core.Order.*;

public class AppConfig {

    public MemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memoryMemberRepository(),
                                    discountPolicy());
    }

}
