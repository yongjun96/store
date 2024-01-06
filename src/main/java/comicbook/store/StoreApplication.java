package comicbook.store;

import comicbook.store.api.MemberApiController;
import comicbook.store.domain.Address;
import comicbook.store.domain.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        //SpringApplication.run(StoreApplication.class, args);

        List<Member> members = new ArrayList<>();
        members.add(new Member(1L, "김용준", new Address("서울", "월드컵북로","58-5"), null));
        members.add(new Member(10L, "김아무개", new Address("서울", "인천북로","78-5"), null));
        members.add(new Member(70L, "하승준", new Address("서울", "강화북로","125-15"), null));
        members.add(new Member(45L, "김창렬", new Address("서울", "부산북로","581-554"), null));
        members.add(new Member(75L, "호미들", new Address("서울", "경기북로","587-55"), null));
        members.add(new Member(15L, "타블로", new Address("서울", "충청북로","548-235"), null));

        Stream<Member> stream = members.stream();

        stream.sorted()
                .forEach(System.out::println);



    }

}
