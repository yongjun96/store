package comicbook.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    //@PersistenceContext 이게 있으면 EntityManager 를 알아서 주입을 해줌
    @PersistenceContext
    private EntityManager em;

    //저장하는 값이 있으면 굳이 리턴값을 만들지 않음 하지만 Id값이 있으면 받는 것도 좋음
    public Long save(MemberTest member){
        em.persist(member);
        return member.getId();
    }

    public MemberTest find(Long id){
        return em.find(MemberTest.class, id);
    }
}
