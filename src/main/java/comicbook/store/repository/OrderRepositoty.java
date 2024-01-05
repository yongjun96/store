package comicbook.store.repository;

import comicbook.store.domain.Order;
import comicbook.store.domain.OrderStatus;
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

        String jpql = "select o from Order o join o.member m";
        List<Order> orderList =null;


        if(orderSearch.getOrderStatus() != null &&
                (orderSearch.getMemberName() == null || orderSearch.getMemberName() == "")){

            jpql += " where o.status =: status";

            orderList = em.createQuery(jpql, Order.class)
                    .setParameter("status", orderSearch.getOrderStatus())
                    .setMaxResults(100)
                    .getResultList();

        }

        else if((orderSearch.getOrderStatus() == null) &&
                (orderSearch.getMemberName() == null || orderSearch.getMemberName() == "")){
            orderList = em.createQuery(jpql, Order.class)
                    .setMaxResults(100)
                    .getResultList();
        }

        else if(orderSearch.getOrderStatus() == null &&
                (orderSearch.getMemberName() != null || orderSearch.getMemberName() != "")){

            jpql += " where m.name =: name";

            orderList = em.createQuery(jpql, Order.class)
                    .setParameter("name", orderSearch.getMemberName())
                    .setMaxResults(100)
                    .getResultList();
        }

        else if(orderSearch.getOrderStatus() != null &&
                (orderSearch.getMemberName() != null || orderSearch.getMemberName() != "")){

            jpql += " where o.status =: status and m.name =: name";

            orderList = em.createQuery(jpql, Order.class)
                    .setParameter("name", orderSearch.getMemberName())
                    .setParameter("status", orderSearch.getOrderStatus())
                    .setMaxResults(100)
                    .getResultList();
        }

        return orderList;
    }
}
