package comicbook.store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class MemberTest {

    @Id @GeneratedValue
    private Long id;
    private String username;

}
