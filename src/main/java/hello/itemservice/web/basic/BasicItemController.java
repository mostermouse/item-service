package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/basic/items")
@Controller
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items" , items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(
            //특정 자원 조회할때 사용하고 여러자원은 리퀘스트파람 사용
            @PathVariable("itemId")  long itemId , Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item" ,item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String save(@RequestParam("itemName") String itemName,
                       @RequestParam("price") Integer price,
                       @RequestParam("quantity") Integer quantity,
                       Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item" , item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item,
                       Model model){
        itemRepository.save(item);
       // model.addAttribute("item" , item); 자동 추가되서 생략 가능
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item,
                            Model model){
        itemRepository.save(item);
        // model.addAttribute("item" , item); 자동 추가되서 생략 가능
        return "basic/item";
    }

   // @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item,
                            Model model){
        itemRepository.save(item);
        // model.addAttribute("item" , item); 자동 추가되서 생략 가능
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item , RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId" , savedItem.getId());
        redirectAttributes.addAttribute("status" , true);
        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item" , item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data" , "Spring!");
        return "basic/literal";
    }

    /*테스트 용*/
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA" , 10000 , 10));
        itemRepository.save(new Item("itemB" , 20000 , 20));
    }
}
