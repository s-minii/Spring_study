package hello.core.member;


// 회원 서비스 구현 클래스
// MemberServiceimpl 클래스의 문제점
// 1. MemoryMemberRepository와 같은 다른 구현체에 의존하고 있다. (DIP 위반)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
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
}
