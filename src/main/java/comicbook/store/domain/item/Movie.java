package comicbook.store.domain.item;

import comicbook.store.domain.item.Item;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Movie extends Item {

    private String director;

    private String actor;

}
