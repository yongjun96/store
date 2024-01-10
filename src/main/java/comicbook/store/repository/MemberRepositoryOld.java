package comicbook.store.repository;

import comicbook.store.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor    // @PersistenceContext나 @Autowired를 자동으로 해줌 final이 붙은 메소드 생성자 만들어 줌
public class MemberRepositoryOld {

    //직접 쓸 일 별로 없음
    //@PersistenceUnit
    //private EnumConstantNotPresentException ebf;


    //@PersistenceContext   //이게 있으면 EntityManager 를 알아서 주입을 해줌
    //@Autowired    //spring Data JPA가 지원해 줘서 사용 가능
    private final EntityManager em;


    //저장하는 값이 있으면 굳이 리턴값을 만들지 않음 하지만 Id값이 있으면 받는 것도 좋음
    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name =: name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
