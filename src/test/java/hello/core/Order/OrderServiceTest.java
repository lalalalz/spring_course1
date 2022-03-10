package hello.core.Order;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
//
//    @Test
//    @DisplayName("정액 할인 정책 테스트")
//    void createOrder() {
//
//        // given
//        Member memberA = new Member(1L, "memberA", Grade.BASIC);
//        Member memberB = new Member(2L, "memberB", Grade.VIP);
//
//
//        // when
//        memberService.join(memberA);
//        memberService.join(memberB);
//
//        Order orderA = orderService.createOrder(memberA.getId(), "firstItem", 10000);
//        Order orderB = orderService.createOrder(memberB.getId(), "secondItem", 10000);
//
//        // then
//        assertThat(orderA.getDiscountPrice()).isEqualTo(0);
//        assertThat(orderA.calculatePrice()).isEqualTo(10000);
//
//        assertThat(orderB.getDiscountPrice()).isEqualTo(1000);
//        assertThat(orderB.calculatePrice()).isEqualTo(9000);
//    }


}
