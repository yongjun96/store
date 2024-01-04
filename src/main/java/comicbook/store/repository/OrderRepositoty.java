package comicbook.store.repository;

import comicbook.store.domain.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoty {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }


    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch){

        String jpql = "select o from Order o join o.member m where o.status =: status and m.name like: name";

        List<Order> orderList = em.createQuery(jpql, Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(100)
                .getResultList();

        return orderList;
    }
}
