package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        //given
        Item a = new Item("1", 10000, 10);
        Item b = new Item("2", 20000, 20);
        itemRepository.save(a);
        itemRepository.save(b);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(a, b);
    }

    @Test
    void updateItem() {
        //given
        Item a = new Item("1", 10000, 10);
        Item savedItem = itemRepository.save(a);
        Long id = savedItem.getId();

        //when
        Item updateParam = new Item("sldkjf", 249895, 3434);
        itemRepository.update(id, updateParam);

        //then
        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void clear() {
        //given

        //when

        //then
    }
}
