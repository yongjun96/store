package comicbook.store.service;

import comicbook.store.domain.item.Book;
import comicbook.store.domain.item.Item;
import comicbook.store.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //전체
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional  //우선권 있음
    public Long saveItem(Item item){
        itemRepository.save(item);
        return item.getId();
    }

    @Transactional
    public void updateItem(Long itemId, Book bookParam){
        Item findItem = itemRepository.findItem(itemId);

    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findItem(itemId);
    }

}
