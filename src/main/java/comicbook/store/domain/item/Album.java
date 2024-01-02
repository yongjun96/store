package comicbook.store.domain.item;

import comicbook.store.domain.item.Item;
import jakarta.persistence.Entity;

@Entity
public class Album extends Item {

    private String artist;

    private String etc;
}
