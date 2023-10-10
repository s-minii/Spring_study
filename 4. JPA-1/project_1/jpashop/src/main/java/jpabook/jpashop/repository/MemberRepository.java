package jpabook.jpashop.repository;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@RequiredArgsConstructor를 사용했을 시, final을 꼭 붙여야함. 아니면 nullPoint 오류!
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    // 단건 조회
    public Member findOne(Long id){
        return em.find(Member.class, id); // id : pk
    }

    // 리스트 조회
    public List<Member> findAll(){
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        return result;
    }

    // 이름으로 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
