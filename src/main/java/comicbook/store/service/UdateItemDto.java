package comicbook.store.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UdateItemDto {
    private String name;
    private int price;
    private int stockQuantity;
}
