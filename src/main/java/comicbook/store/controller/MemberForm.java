package comicbook.store.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    @Size(min = 2, message = "최소 한 글자 이상 입력해주세요")
    private String name;

    @NotEmpty(message = "도시를 입력해주세요.")
    private String city;
    @NotEmpty(message = "도로명을 입력해주세요.")
    private String street;
    private String zipcode;


}
