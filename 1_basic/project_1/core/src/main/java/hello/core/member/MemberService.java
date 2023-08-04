// 회원 서비스 인터페이스

package hello.core.member;

public interface MemberService {


    // 회원가입
    void join(Member Member);


    //회원찾기
    Member findMember(Long memberId);
}
