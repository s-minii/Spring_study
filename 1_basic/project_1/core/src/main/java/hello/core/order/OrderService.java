package hello.core.order;



//최종 주문 정보를 반환
public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
