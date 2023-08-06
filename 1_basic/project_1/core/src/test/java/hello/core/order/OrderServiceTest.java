package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// orderSerivce 테스트
public class OrderServiceTest {


    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void BeforeEach(){
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
        orderService = appconfig.orderService();
    }

    @Test
    void createOrder(){
        // given
        // long은 null을 사용할 수 없다.
        // 객체 생성 단계에서 DB에 null값을 넣을 수도 있어서 Long으로 선언함.
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}