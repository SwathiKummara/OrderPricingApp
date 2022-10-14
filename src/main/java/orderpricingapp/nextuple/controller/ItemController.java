package orderpricingapp.nextuple.controller;

import orderpricingapp.nextuple.exception.AlreadyExistsException;
import orderpricingapp.nextuple.exception.DoesNotMatchException;
import orderpricingapp.nextuple.exception.NotFoundException;
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
    public Item getItemById(@PathVariable String itemKey) throws NotFoundException {
      return itemService.getByItemKey(itemKey);
    }

    @GetMapping("/{organizationCode}/{itemId}")
    public Optional<Item> getByCodeAndItem(@PathVariable ("organizationCode") String code, @PathVariable("itemId") String id) throws NotFoundException, DoesNotMatchException {
       return itemService.getbycodeandid(code,id);
    }


    @PostMapping
    public String postItem(@RequestBody Item item) throws NotFoundException, AlreadyExistsException {
          return itemService.saveItem(item);
    }
    @DeleteMapping("/{itemKey}")
    public String deleteitem(@PathVariable("itemKey") String itemKey) throws NotFoundException {
        return itemService.delete(itemKey);

    }

    @DeleteMapping("/{organizationCode}/{itemId}")
    public String deletByCodeAndId(@PathVariable ("organizationCode") String code, @PathVariable("itemId") String id) throws NotFoundException, DoesNotMatchException {
        return itemService.deleteBycodeAndId(code, id);

    }
    @PutMapping("/{itemKey}")
    public Item updatelist(@RequestBody Item item, @PathVariable ("itemKey") String key) throws NotFoundException {
        return itemService.update(key, item);

    }
    @PatchMapping("/{itemKey}")
    public Item patch(@RequestBody Item item, @PathVariable("itemKey") String key) throws NotFoundException {
        return itemService.patch(key,item);
    }




}
