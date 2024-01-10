package comicbook.store.service;

import comicbook.store.domain.Member;
import comicbook.store.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  //읽기 기능을 많이 사용하는 경우는 읽기를 디폴트로 설정
//@AllArgsConstructor
@RequiredArgsConstructor    // 인젝션이 끝난 final 메소드만!!! 생성자를 만들어 줌
public class MemberService {

    private final MemberRepository memberRepository;    //final 권장 (컴파일 시점에 체크 가능)

//    @Autowired  //하나만 있다면 @Autowired를 자동으로 해줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     */
    @Transactional      //(readOnly = false) --> 디폴트임
    public Long join(Member member){
        validateDuplicateMember(member);    // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //중복회원이라면 예외
        //멀티쓰레드를 고려해서 중복으로 같은 값이 한번에 들어올 경우를 대비해 name을 유니크로 잡아야 함
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member finOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }


    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }
}
