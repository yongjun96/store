package comicbook.store.service;

import comicbook.store.domain.Address;
import comicbook.store.domain.Member;
import comicbook.store.domain.Order;
import comicbook.store.domain.OrderStatus;
import comicbook.store.domain.item.Book;
import comicbook.store.domain.item.Item;
import comicbook.store.exception.NotEnoughStockException;
import comicbook.store.repository.OrderRepositoty;
import jakarta.persistence.EntityManager;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepositoty orderRepositoty;

    private Book createBook (String name, int price, int stockQuantity){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember (String name, Address address){
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        em.persist(member);
        return member;
    }

    @Test
    public void 상품주문() throws Exception{

        //given
        Member member = createMember("회원1", new Address("서울", "강가", "123-123"));

        Book book = createBook("만화책", 10000, 10);

        int orderCount = 2;

        //when
        Long id = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepositoty.findOne(id);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
    }


    @Test
    public void 상품주문_재고수량초과() throws Exception{

        //given
        Member member = createMember("회원1", new Address("서울", "강가", "123-123"));
        Item item = createBook("만화책", 10000, 10);

        int orderCount = 11;

        //when
        NotEnoughStockException e = assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        }, "재고 수령 부족 예외가 발생하지 않는 케이스임.");
    }


    @Test
    public void 주문취소() throws Exception{

        //given
        Member member = createMember("회원1", new Address("서울", "강가", "123-123"));
        Item item = createBook("만화책", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepositoty.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL 이다.");
        assertEquals(10, item.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
    }
}