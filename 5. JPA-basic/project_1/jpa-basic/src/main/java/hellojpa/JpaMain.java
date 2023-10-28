package hellojpa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HellsoJPA");

            //영속 (이때는 쿼리 안 나감)
            em.persist(member);
            
            // commit하는 시점에 쿼리가 실행
            tx.commit();
        } catch (Exception e){
            em.close();
        } finally {
            emf.close();
        }

    }
}
