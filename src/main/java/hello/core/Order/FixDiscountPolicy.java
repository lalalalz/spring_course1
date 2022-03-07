package hello.core.Order;

import hello.core.Member.Grade;
import hello.core.Member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.BASIC)  return 0;
        else                                  return this.discountFixAmount;
    }
}
