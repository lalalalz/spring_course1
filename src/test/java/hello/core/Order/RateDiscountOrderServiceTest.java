package hello.core.Order;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountOrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    @DisplayName("정률 할인 정책 주문 서비스 테스트")
    void createOrder() {

        // given
        Member memberA = new Member(1L, "memberA", Grade.BASIC);
        Member memberB = new Member(2L, "memberB", Grade.VIP);

        // when
        memberService.join(memberA);
        memberService.join(memberB);

        Order orderA = orderService.createOrder(memberA.getId(), "itemA", 10000);
        Order orderB = orderService.createOrder(memberB.getId(), "itemB", 10000);

        // then
        Assertions.assertThat(orderA.getDiscountPrice()).isEqualTo(0);
        Assertions.assertThat(orderB.getDiscountPrice()).isEqualTo(1000);

    }

}
