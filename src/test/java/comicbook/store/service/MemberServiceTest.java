package comicbook.store.service;

import comicbook.store.domain.Member;
import comicbook.store.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)    //Junit5는 없어도 됨 ㅋㅋ
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(false)
    void 회원가입() throws Exception{

        //given
        Member member = new Member();
        member.setName("yongjun");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));

    }


    @Test
    void 중복_회원_예외() throws Exception{

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);    //예외가 발생해야 한다.
        }, "중복 회원 예외가 발생하지 않는 케이스임.");
    }

}