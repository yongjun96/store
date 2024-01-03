package comicbook.store.service;

import comicbook.store.domain.item.Book;
import comicbook.store.domain.item.Item;
import comicbook.store.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;
    @Autowired EntityManager em;

    @Test
    @Rollback(false)
    void 상품등록(){

        Book book = new Book();
        book.setName("나루토");
        book.setPrice(1000);
        book.setIsbn("대원");

        Long itemId = itemRepository.save(book);

        assertEquals(itemId, itemService.saveItem(book));
    }
}