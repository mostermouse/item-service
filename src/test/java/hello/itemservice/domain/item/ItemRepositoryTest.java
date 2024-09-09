package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }
    @Test
    void save(){
        //given
        Item item = new Item("itemA" , 1000 , 10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findByID = itemRepository.findById(item.getId());
        assertThat(findByID).isEqualTo(saveItem);
    }

    @Test
    void findAll(){
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }

    @Test
    void updateItem(){
        //givne
        Item itemA = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(itemA);
        Long itemId = saveItem.getId();
        //when
        Item updateParam = new Item("itemB" , 20000, 20);
        itemRepository.update(itemId, updateParam);
        //then
        Item byId = itemRepository.findById(itemId);
        assertThat(byId.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(byId.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(byId.getQuantity()).isEqualTo(updateParam.getQuantity());

    }
}