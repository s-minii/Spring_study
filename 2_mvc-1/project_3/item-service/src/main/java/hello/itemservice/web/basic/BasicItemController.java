package hello.itemservice.web.basic;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    // 상품 목록
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    // 상품 세부 내용
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 상품 등록 폼 연결
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    // 등록 수정 폼 연결
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    // 상품 수정 후, 저장 버튼을 눌렀을 때 리다이렉트처리
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    // 상품 등록 폼 -> 상품 등록 클릭 시
 //   @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){

        itemRepository.save(item);
        //model.addAttribute("item", item);

        return "basic/item";
    }

    // 보통 이 정도로 사용하면 된다!
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute("item") Item item){
        itemRepository.save(item);

        return "basic/item";
    }


//    @PostMapping("/add")
    public String addItemV4(Item item){
        itemRepository.save(item);

        return "basic/item";
    }

    // Redirect 버전 (상품 등록 후, 새로고침 눌렀을 때 같은 상품이 지속적으로 등록되는 것을 방지)
    @PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }
}