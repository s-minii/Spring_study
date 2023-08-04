package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;


// 고정 할인 정책
public class FixDiscountPloiecy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 고정 1000원 할인
    @Override
    public int discount(Member member, int price){

        // enum은 비교할 때 == 사용
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        else{
            return 0;
        }
    }
}