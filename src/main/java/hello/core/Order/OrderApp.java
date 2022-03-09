package hello.core.Order;

import hello.core.AppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        memberService.join(new Member(1L, "memberA", Grade.VIP));
        Order order = orderService.createOrder(1L, "itemA", 10000);
        System.out.println("order : " + order);
    }
}
