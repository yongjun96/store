package comicbook.store.domain.item;

import comicbook.store.domain.Category;
import comicbook.store.domain.Member;
import comicbook.store.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();


    // 비즈니스 로직 : setter로 하는게 아닌 필요한 경우 사용할 수 있는 이런 메소드들로 구현해서 사용
    /**
    *제고 수량을 증가
    */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     *제고 수량 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
