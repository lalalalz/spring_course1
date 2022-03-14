package hello.core.Order;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    /***
     * @param member
     * @param price
     * @return 할인되는 금액을 반환해준다.
     */
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }

}
