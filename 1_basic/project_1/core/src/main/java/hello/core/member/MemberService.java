package hello.core.member;

public interface MemberService {

    void join(Member Member);

    Member findMember(Long memberId);
}
