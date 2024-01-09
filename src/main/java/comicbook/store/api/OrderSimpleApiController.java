package comicbook.store.api;

import comicbook.store.domain.Address;
import comicbook.store.domain.Order;
import comicbook.store.domain.OrderStatus;
import comicbook.store.repository.OrderRepository;
import comicbook.store.repository.OrderSearch;
import comicbook.store.repository.order.simplequery.OrderSimpleQueryDto;
import comicbook.store.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne (ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RequiredArgsConstructor
@RestController
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("api/v1/simple-orders")
    public List<Order> orderV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        // 회원 N + 배송 N + 1
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        //다건 검색
         List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

          return result;
    }


    @GetMapping("api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3(){
        // 회원 N + 배송 N + 1
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

        //다건 검색
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }


    //사실상 성능 향상은 미비하고 재사용성이 떨어짐.
    @GetMapping("api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4(){
        // 회원 N + 배송 N + 1
       return orderSimpleQueryRepository.findOrderDtos();

    }

    @Data
    static class SimpleOrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName(); //LAZY 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); //LAZY 초기화
        }
    }
}
