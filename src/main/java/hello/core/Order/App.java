package hello.core.Order;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;

public class App {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        memberService.join(new Member(1L, "memberA", Grade.VIP));

        Order order = orderService.createOrder(1L, "itemA", 10000);

        System.out.println("order : " + order);
    }
}
