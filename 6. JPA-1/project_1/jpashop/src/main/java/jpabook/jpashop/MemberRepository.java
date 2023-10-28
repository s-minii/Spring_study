package jpabook.jpashop;
// DAO랑 비슷함 (엔티티 찾아줌)

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


// Repository : DAO랑 비슷함 (엔티티 찾아줌)
@Repository
@Transactional
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
