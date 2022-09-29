package orderpricingapp.nextuple.controller;

import orderpricingapp.nextuple.exception.ItemException;
import orderpricingapp.nextuple.model.Item;
import orderpricingapp.nextuple.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;
    private static Map<String, Item> itemRepo = new HashMap<>();
    @GetMapping
    public List<Item> getAllItems () {
        return itemService.getAllItems();
    }

    @GetMapping("/{itemKey}")
    public Item getItemById(@PathVariable String itemKey) throws ItemException {
      return itemService.getByItemKey(itemKey);
    }

    @GetMapping("/{organizationCode}/{itemId}")
    public Optional<Item> getByCodeAndItem(@PathVariable ("organizationCode") String code, @PathVariable("itemId") String id) throws ItemException {
       return itemService.getbycodeandid(code,id);
    }


    @PostMapping
    public String postItem(@RequestBody Item item) throws ItemException {
          return itemService.saveItem(item);
    }
    @DeleteMapping("/{itemKey}")
    public String deleteitem(@PathVariable("itemKey") String itemKey) throws ItemException {
        return itemService.delete(itemKey);

    }

    @DeleteMapping("/{organizationCode}/{itemId}")
    public String deletByCodeAndId(@PathVariable ("organizationCode") String code, @PathVariable("itemId") String id) throws ItemException {
        return itemService.deleteBycodeAndId(code, id);

    }
    @PutMapping("/{itemKey}")
    public Item updatelist(@RequestBody Item item, @PathVariable ("itemKey") String key) throws ItemException {
        return itemService.update(key, item);

    }
    @PatchMapping("/{itemKey}")
    public Item patch(@RequestBody Item item, @PathVariable("itemKey") String key) throws ItemException {
        return itemService.patch(key,item);
    }




}
