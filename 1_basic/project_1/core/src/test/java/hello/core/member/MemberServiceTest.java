package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// 테스트는 필수!!
// MemberService를 테스트함
public class MemberServiceTest {

    //객체 생성
    MemberService memberService;

    // BeforeEach : 각 테스트 실행 전에 실행되게 한다.
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    // 회원가입에 대한 테스트 코드
    void join() {
        // given (어떠한 환경이 주어졌을 때)
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when (언제)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then (검증)
        // Assertions.assertThat(member)은 member 객체를 검사하고, 비교하는 시작점
        // isEqualTo(findMember)은 member 객체와 findMember객체를 비교하여 같은지 판단
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}