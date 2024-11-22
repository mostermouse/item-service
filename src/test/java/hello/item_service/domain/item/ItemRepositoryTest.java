package hello.item_service.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clear();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA" , 10000 , 10);
        //when
        Item save = itemRepository.save(item);
        //then
        Item findById = itemRepository.findById(item.getId());
        assertThat(findById).isEqualTo(save);
    }

    @Test
    void findAll(){
        //given
        Item item = new Item("itemA" , 10000 , 10);
        Item item2 = new Item("itemB" , 10000 , 10);
        itemRepository.save(item);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item , item2);
    }

    @Test
    void updateItem(){
        //given
        Item item = new Item("ItemA" , 10000 , 10);
        //when
        Item save = itemRepository.save(item);
        Long itemId = save.getId();

        Item item2 = new Item("ItemB" , 1000 ,20);
        itemRepository.update(itemId , item2);
        //then
        Item findById = itemRepository.findById(itemId);
        assertThat(findById.getItemName()).isEqualTo(item2.getItemName());
        assertThat(findById.getPrice()).isEqualTo(item2.getPrice());
        assertThat(findById.getQuantity()).isEqualTo(item2.getQuantity());

    }
}
