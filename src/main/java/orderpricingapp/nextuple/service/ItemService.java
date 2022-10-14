package orderpricingapp.nextuple.service;

import orderpricingapp.nextuple.exception.AlreadyExistsException;
import orderpricingapp.nextuple.exception.DoesNotMatchException;
import orderpricingapp.nextuple.exception.NotFoundException;
import orderpricingapp.nextuple.model.Item;
import orderpricingapp.nextuple.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;


    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }



    public Item getByItemKey(String itemKey) throws NotFoundException {
        return itemRepository.findById(itemKey).orElseThrow(() -> new NotFoundException("Item with key doesn't exists " + itemKey));
    }

    public List<Item> getByItemAndCode(@RequestParam String code,@RequestParam String key){
        return (List<Item>) itemRepository.findByOrganizationCodeAndItemKey(code,key);
    }

    public String saveItem(Item item) throws AlreadyExistsException {
        Item item1 = itemRepository.findByItemId(item.getItemId());
        if(item1 == null){
            itemRepository.save(item);
            return "item added Successfully";
        }
        else {
            throw new AlreadyExistsException("item already exists!");
        }

    }
    public String delete(String key) throws NotFoundException {
        Item existinglist = itemRepository.findById(key).orElseThrow(()-> new NotFoundException("Item not found with key :" + key ) );
        itemRepository.delete(existinglist);
        return "Item is deleted successsfully";
    }

    public String deleteBycodeAndId(String code, String id) throws NotFoundException, DoesNotMatchException {
        Item itemOptional = itemRepository.findByOrganizationCodeAndItemId(code, id);
        Optional<Item> chekingOptionalitem = Optional.ofNullable(itemOptional);
        if (chekingOptionalitem.isEmpty()){
            throw new DoesNotMatchException("itemid "+id +" with organizationcode "+code+ " doesn't match.");
        }
        else {
            itemRepository.delete(itemOptional);
            return "item deleted succesfully";
        }
    }
    public Item update(String key , Item item) throws NotFoundException {
        Item existinglist = itemRepository.findById(key).orElseThrow(() -> new NotFoundException("Item not found with key  "+key));
        existinglist.setItemId(item.getItemId());
        existinglist.setItemDescription(item.getItemDescription());
        existinglist.setCategory(item.getCategory());
        existinglist.setStatus(item.getStatus());
        existinglist.setType(item.getType());
        existinglist.setUnitOfMeasure(item.getUnitOfMeasure());
        existinglist.setOrganizationCode(item.getOrganizationCode());
        return itemRepository.save(existinglist);

    }

    public Item patch(String key , Item item) throws NotFoundException {

        Item existinglist = itemRepository.findById(key).orElseThrow(() -> new NotFoundException("Item not found with id :" + key));

        existinglist.setCategory(item.getCategory());

        return  itemRepository.save(existinglist);
    }

    public Optional<Item> getbyitemId(String id) throws NotFoundException {
        Optional<Item> existingItemId = (Optional<Item>) Optional.ofNullable(itemRepository.findByItemId(id));
        if (existingItemId.isEmpty()){
            throw new NotFoundException("item id does not exists with id "+ id);
        }
        else {
            return existingItemId;
        }
    }


    public Optional<List<Item>> getbyOrganizationcode(String code) throws NotFoundException {
        Optional<List<Item>> existingOrganization = itemRepository.findByOrganizationCode(code);
        if (existingOrganization.isEmpty()){
            throw new NotFoundException("item organization code does not exists with code"+code);
        }
        else {
            return existingOrganization;
        }
    }
    public Optional<Item> getbycodeandid(String code,String id) throws NotFoundException, DoesNotMatchException {
        Optional<Item> existingcodeandid = Optional.ofNullable(itemRepository.findByOrganizationCodeAndItemId(code, id));
        if (existingcodeandid.isEmpty()){
            throw new DoesNotMatchException("itemid "+id +" with organizationcode "+code+ " doesn't match.");
        }
        else {
            return existingcodeandid;
        }
    }

}



