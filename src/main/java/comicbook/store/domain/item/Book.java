package comicbook.store.domain.item;

import comicbook.store.domain.item.Item;
import jakarta.persistence.Entity;

@Entity
public class Book extends Item {

    private String author;

    private String isbn;

}
