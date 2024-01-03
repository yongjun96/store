package comicbook.store.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter     //값 타입은 Getter만 제공 해서 쓰는게 이상적
public class Address {

    protected Address(){}   //JPA는 public, protected로 사용

    private String city;

    private String street;

    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
