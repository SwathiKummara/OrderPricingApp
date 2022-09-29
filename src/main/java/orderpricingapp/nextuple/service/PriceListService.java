package orderpricingapp.nextuple.service;

import com.orderPricingApp.springboot.exception.PricelistException;
import orderpricingapp.nextuple.model.PriceList;
import orderpricingapp.nextuple.repository.ItemRepository;
import orderpricingapp.nextuple.repository.PriceLineListRepository;
import orderpricingapp.nextuple.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PriceListService {
    @Autowired
    PriceListRepository priceListRepository;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PriceLineListRepository priceLineListRepository;
    public List<PriceList> getAll(){
        return priceListRepository.findAll();
    }

    public PriceList getByKey(String key) throws com.orderPricingApp.springboot.exception.PricelistException {
        return priceListRepository.findById(key).orElseThrow(()->new com.orderPricingApp.springboot.exception.PricelistException("Pricelist does not exists with key"+key));
    }
    public List<PriceList> getByPricelitKey(String key) throws com.orderPricingApp.springboot.exception.PricelistException {
        return (List<PriceList>) priceListRepository.findByPriceListKey(key);
    }

    public List<PriceList> getByNameandCode(@RequestParam String code, @RequestParam String name) {
        return priceListRepository.findByOrganizationCodeAndPriceListName(code,name);


    }
    public List<PriceList> getBystatusandCode(@RequestParam String status, @RequestParam String name) {
        return priceListRepository.findByActiveAndOrganizationCode(status,name);
    }

    public String saveList(PriceList priceList) throws PricelistException {
        Optional<PriceList> priceList1 = priceListRepository.findById(priceList.getPriceListKey());

        if(priceList1 == null){
            priceListRepository.save(priceList);
            return "pricelist added Successfully";
        }
        else {
            throw new PricelistException("pricelist already exists!");
        }
   }


    public PriceList update(String key , PriceList priceList) throws PricelistException {
        PriceList existinglist = priceListRepository.findById(key).orElseThrow(() -> new PricelistException("Pricelist not found with id :" + key));
        existinglist.setActive(priceList.getActive());
        existinglist.setPriceListName(priceList.getPriceListName());
        existinglist.setEndDate(priceList.getEndDate());
        existinglist.setStartDate(priceList.getStartDate());
        existinglist.setOrganizationCode(priceList.getOrganizationCode());
        return  priceListRepository.save(existinglist);
    }
    public PriceList patch(String key , PriceList priceList) throws PricelistException {

        PriceList existinglist = priceListRepository.findById(key).orElseThrow(() -> new PricelistException("Pricelist not found with id :" + key));
        existinglist.setPriceListName(priceList.getPriceListName());
        return  priceListRepository.save(existinglist);

    }
    public Date getStartDate(Date date) throws PricelistException{
        return  priceListRepository.findByStartDate(date);
    }
public Date getEnddate(Date date) throws  PricelistException{
        return  priceListRepository.findByEndDate(date);
}
//public String delete(String key) throws PricelistException {
//Optional<PriceList> optionalPriceList = priceListRepository.findById(String.valueOf(priceLineListRepository.findById(key)));
//
//if(optionalPriceList.isPresent()){
//    throw new PricelistException("price list with key "+key+" present in pricelistline list so can't delete");
//}
//        priceListRepository.delete(optionalPriceList);
//    return "Pricelist is deleted successsfully";}

}
