package comicbook.store.repository;

import comicbook.store.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //select m from Member where m.name = ? -> 알아서 만들어 줌 ㄷㄷ;
    List<Member> findByName(String name);
}
