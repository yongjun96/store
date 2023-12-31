package comicbook.store;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional  //테스트에 있으면 테스트가 끝나고 롤백을 함 (데이터가 들어가 있으면 반복적인 테스트를 못하니까)
    @Rollback(value = false)    // 자동 롤백을 막음
    public void testMember() throws Exception {
        //given
        MemberTest member = new MemberTest();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        MemberTest findMember = memberRepository.find(saveId);

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);   // true : 같은 영속성 컨텍스트 안에서는 ID같이 같으면 같은 Entity
    }

}