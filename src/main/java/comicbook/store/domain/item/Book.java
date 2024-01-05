package comicbook.store.domain.item;

import comicbook.store.domain.item.Item;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends Item {

    private String author;

    private String isbn;
}
