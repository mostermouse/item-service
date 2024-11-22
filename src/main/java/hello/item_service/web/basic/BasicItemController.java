package hello.item_service.web.basic;

import hello.item_service.domain.item.Item;
import hello.item_service.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String item(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items" ,items);
        return "basic/items";

    }

    //테스트용 데이터 추가
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("ItemA" , 10000 , 10));
        itemRepository.save(new Item("ItemB" , 20000 , 20));
    }
}
