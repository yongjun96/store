package comicbook.store.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import comicbook.store.domain.Address;
import comicbook.store.domain.Member;
import comicbook.store.domain.Order;
import comicbook.store.domain.item.Item;
import comicbook.store.service.ItemService;
import comicbook.store.service.MemberService;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final ItemService itemService;


    @GetMapping("api/v1/members")
    public List<Member> membersV1(){

        List<Member> members = memberService.findMembers();
        return members;
    }

    @FunctionalInterface
    interface My{
        int max(int a, int b);
    }


    @GetMapping("api/v2/members")
    public Result membersV2(){

        List<Member> findMembers = memberService.findMembers();
        List<Item> findItems = itemService.findItems();

        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getId(), m.getName()))
                .collect(Collectors.toList());

        List<ItemDto> itmeCollect = findItems.stream()
                .map(i -> new ItemDto(i.getId(), i.getName()))
                .collect(Collectors.toList());


        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T, I>{

        private int size;

        private T data;

       // private I itemData;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private Long id;

        private String name;
    }

    @Data
    @AllArgsConstructor
    static class ItemDto{
        private Long id;

        private String name;
    }



    @GetMapping()


    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request){

       memberService.update(id, request.getName());
       Member findMember = memberService.finOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }



    @Data
    static class CreateMemberRequest{

        @Size(min = 2, message = "이름의 값을 최소 두글자 이상 입력해주세요.")
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{

        private Long id;

        @Size(min = 2, message = "이름의 값을 최소 두글자 이상 입력해주세요.")
        private String name;
    }

    @Data
    static class UpdateMemberRequest{

        @Size(min = 2, message = "이름의 값을 최소 두글자 이상 입력해주세요.")
        private String name;
    }


}
