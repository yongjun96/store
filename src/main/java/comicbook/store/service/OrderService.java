package comicbook.store.service;

import comicbook.store.domain.Delivery;
import comicbook.store.domain.Member;
import comicbook.store.domain.Order;
import comicbook.store.domain.OrderItem;
import comicbook.store.domain.item.Item;
import comicbook.store.repository.ItemRepository;
import comicbook.store.repository.MemberRepository;
import comicbook.store.repository.OrderRepository;
import comicbook.store.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findItem(itemId);

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        //주문 상품 입력
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }


    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){

        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문 취소
        order.cancel();
    }


    /**
     * 주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch){

        return orderRepository.findAllByString(orderSearch);

    }
}
