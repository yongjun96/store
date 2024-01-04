package comicbook.store.domain.item;

import comicbook.store.domain.item.Item;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie extends Item {

    private String director;

    private String actor;

}
