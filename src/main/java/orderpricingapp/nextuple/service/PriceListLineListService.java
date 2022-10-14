package orderpricingapp.nextuple.service;


import orderpricingapp.nextuple.exception.AlreadyExistsException;
import orderpricingapp.nextuple.exception.DoesNotMatchException;
import orderpricingapp.nextuple.exception.NotFoundException;
import orderpricingapp.nextuple.model.Item;
import orderpricingapp.nextuple.model.PriceList;
import orderpricingapp.nextuple.model.PricelistLineList;
import orderpricingapp.nextuple.repository.ItemRepository;
import orderpricingapp.nextuple.repository.PriceLineListRepository;
import orderpricingapp.nextuple.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceListLineListService {

    @Autowired
    PriceLineListRepository priceLineListRepository;
    @Autowired
    PriceListRepository priceListRepository;
    @Autowired
    ItemRepository itemRepository;
    public List<PricelistLineList> getAllListsWithPagination(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo -1,pageSize);

        return priceLineListRepository.findAll(pageable).getContent();
    }

    public PricelistLineList add(PricelistLineList pricelistLineList) throws DoesNotMatchException, NotFoundException, AlreadyExistsException {
        Optional<PricelistLineList> optionalPricelistLineList = priceLineListRepository.findByOrganizationCodeAndItemkeyAndPricelistkey(pricelistLineList.getOrganizationCode(),pricelistLineList.getItemkey(),pricelistLineList.getPricelistkey());

        if (optionalPricelistLineList.isPresent()){
            throw new AlreadyExistsException("price list line list is already exists");
        }
        Optional<PriceList> optionalPriceList = priceListRepository.findById(pricelistLineList.getPricelistkey());
        if(optionalPriceList.isEmpty()){
            throw new AlreadyExistsException("price list with key "+ pricelistLineList.getPricelistkey()+" does not exists");
        }
        if(!optionalPriceList.get().getOrganizationCode().equalsIgnoreCase(pricelistLineList.getOrganizationCode())){
            throw new DoesNotMatchException("organization code of  pricelist doesn't match organization code of pricelistlinelist");
        }
        Optional<Item> optionalItem = itemRepository.findById(pricelistLineList.getItemkey());
        if(optionalItem.isEmpty()){
            throw new NotFoundException("Item with key "+ pricelistLineList.getItemkey()+"does not exists");
        }
        if(!optionalItem.get().getOrganizationCode().equalsIgnoreCase(pricelistLineList.getOrganizationCode())){
            throw new DoesNotMatchException("organization code of  Item doesn't match organization code of pricelistlinelist");
        }
        return priceLineListRepository.save(pricelistLineList);
    }  public PricelistLineList getByKey(String key) throws AlreadyExistsException, NotFoundException {
        return priceLineListRepository.findById(key).orElseThrow(()->new NotFoundException("Pricelist does not exists with key "+key));
    }

    public List<PricelistLineList> getByitemKey(String key) throws AlreadyExistsException {
        return  priceLineListRepository.findByItemkey(key);
    }

    public PricelistLineList update(String key , PricelistLineList pricelistLineList) throws  NotFoundException {
        PricelistLineList existinglist = priceLineListRepository.findById(key).orElseThrow(() -> new NotFoundException("Pricelist line list not found with id :" + key));
        existinglist.setListPrice(pricelistLineList.getListPrice());
        existinglist.setUnitPrice(pricelistLineList.getUnitPrice());
        return  priceLineListRepository.save(existinglist);
    }
    public PricelistLineList patch(String key , PricelistLineList pricelistLineList) throws  NotFoundException {

        PricelistLineList existinglist = priceLineListRepository.findById(key).orElseThrow(() -> new NotFoundException("User not found with id :" + key));

        existinglist.setUnitPrice(pricelistLineList.getUnitPrice());
        existinglist.setListPrice(pricelistLineList.getListPrice());
        return  priceLineListRepository.save(existinglist);
    }
    public String delete(String key) throws  NotFoundException {
        PricelistLineList existingkey = priceLineListRepository.findById(key).orElseThrow(() -> new NotFoundException("User not found with key :" + key));
        priceLineListRepository.delete(existingkey);
        return "Pricelistlinelist is deleted successsfully";
    }
}
//TODO posting item(key)