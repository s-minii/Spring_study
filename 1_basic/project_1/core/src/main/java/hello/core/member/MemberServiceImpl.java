package hello.core.member;


// 회원 서비스 구현 클래스
// MemberServiceimpl 클래스의 문제점
// 1. MemoryMemberRepository와 같은 다른 구현체에 의존하고 있다. (DIP 위반)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class) 과 비슷한 역할을 함.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    // 회원가입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원조회
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}