package comicbook.store.domain.item;

import comicbook.store.domain.item.Item;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Album extends Item {

    private String artist;

    private String etc;
}
