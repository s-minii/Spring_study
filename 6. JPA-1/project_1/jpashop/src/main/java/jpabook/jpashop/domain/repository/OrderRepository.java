package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    //단건 조회
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 회원명, 주문상태로 검색하기
    // public List<Order> findAll(){}
}
